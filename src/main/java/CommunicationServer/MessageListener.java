package CommunicationServer;

import DataServer.VehicleDatabaseDAO;
import Message.Enum.RequestID;
import Message.LeitsystemRequest;
import Message.MessageConfig;
import UserInterface.UserInterface;
import Utils.AlertUtils;
import com.y4n.UDP.UDPUnicast;
import com.y4n.Utils.MessageUtils.Enum.RequestType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Calendar;

public class MessageListener extends Thread {
    private UDPUnicast listener;
    private VehicleDatabaseDAO vehicleDatabaseDAO;
    private UserInterface UI;

    public void setUI(UserInterface UI) {
        this.UI = UI;
    }

    public MessageListener() throws IOException {
        this.listener = new UDPUnicast();
    }

    public MessageListener(int port) throws IOException {
        this.listener = new UDPUnicast(port);
    }

    @Override
    public void run() {
        try {
            while (true) {
                DatagramPacket packet = this.listener.getPacket(MessageConfig.MESSAGE_LENGTH);
                LeitsystemRequest request = new LeitsystemRequest(packet.getData());
                request.setHeaderLength(MessageConfig.MESSAGE_HEADER_LENGTH);
                if (packetIsFromLeitsystemClient(request)) {
                    logTime();
                    logReceivedPacket(packet);
                    LeitsystemRequestHandler requestHandler = new LeitsystemRequestHandler(packet);
                    requestHandler.setVehicleDatabaseDAO(this.vehicleDatabaseDAO);
                    requestHandler.setUI(this.UI);
                    requestHandler.start();
                }
// this case is only to test
//                else if(packet.getData().toString().isEmpty()==false){
//                    System.out.println(DataFormatUtils.byteArrToStr(packet.getData()));
//                    System.out.println(DataFormatUtils.byteArrToHEXCharList(packet.getData()));
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertExceptionStackTrace(e);
        }
    }

    public int getPort() {
        return this.listener.getPort();
    }

    private boolean packetIsFromLeitsystemClient(LeitsystemRequest request) {
        byte requestTypeCode = request.getHeader()[MessageConfig.MESSAGE_TYPE_POSITION_IN_HEADER];
        boolean requestTypeIsValid = false;
        byte requestIDCode = request.getHeader()[MessageConfig.MESSAGE_ID_POSITION_IN_HEADER];
        boolean requestIDIsValid = false;

        for (RequestType validRequestType :
                RequestType.values()) {
            if (requestTypeCode == validRequestType.ordinal()) requestTypeIsValid = true;
        }
        for (RequestID validRequestID :
                RequestID.values()) {
            if (requestIDCode == validRequestID.ordinal()) requestIDIsValid = true;
        }
        return requestIDIsValid && requestTypeIsValid;
    }

    public void setVehicleDatabaseDAO(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
    }

    //log function
    private void logReceivedPacket(DatagramPacket packet) {
        String message = "Request from Leitsystem Client at: " + packet.getAddress().getHostAddress() + ":"
                + packet.getPort() + "Handling Request";
        System.out.println(message);
        this.UI.log(message);
    }

    public void logTime() {
        String message = "\n\n------------------" + Calendar.getInstance().getTime() + "-----------------------------------";
        System.out.println(message);
        this.UI.log(message);
    }
}
