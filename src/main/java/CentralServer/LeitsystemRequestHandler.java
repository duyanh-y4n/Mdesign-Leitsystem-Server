package CentralServer;

import CentralServer.CommunicationServer.MessageUnicastSender;
import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import Message.Enum.RequestID;
import Message.Enum.ResponseID;
import Message.LeitsystemRequest;
import Message.LeitsystemResponse;
import Message.MessageConfig;
import TrafficSystemLogic.CrossroadList;
import TrafficSystemLogic.Trafficsystem;
import com.y4n.Utils.DataFormatUtils;

import java.net.BindException;
import java.net.DatagramPacket;
import java.util.Random;

public class LeitsystemRequestHandler extends Thread {
    private DatagramPacket requestPacket;
    private LeitsystemRequest request;
    private LeitsystemResponse response;
    private VehicleDatabaseDAO vehicleDatabaseDAO;

    public LeitsystemRequestHandler(DatagramPacket requestPacket) {
        this.requestPacket = requestPacket;
        this.request = new LeitsystemRequest(requestPacket.getData());
        this.request.setHeaderLength(MessageConfig.MESSAGE_HEADER_LENGTH);
    }

    public void handleRequest() {
        byte requestIDCode = this.request.getHeader()[MessageConfig.MESSAGE_ID_POSITION_IN_HEADER];
        RequestID requestID = RequestID.values()[requestIDCode];

        //TODO: response will be parse here (Logic of the system)
        switch (requestID) {
            case NONE:
                System.out.println(ResponseID.NONE + " to " + requestID + ":");
                break;
            case REGISTER_REQ:
                System.out.println(ResponseID.REGISTER_ID_RES + " to " + requestID + ":");
                logRequest();
                handleRegisterReq();
                sendResponse();
                break;
            case UPDATE_CAR_STATE_REQ:
                System.out.println(ResponseID.DRIVE_PERMISSTION_RES + " to " + requestID + ":");
                logRequest();
                if (handleCarStateReq()) sendResponse();
                break;
            default:
                System.out.println("none of Request ID was found!");
                break;
        }
    }

    private void sendResponse() {
        int port = ServerConfig.UNICAST_SENDER_PORT;
        while (true) {
            try {
                MessageUnicastSender sender = new MessageUnicastSender(port);
                sender.send(this.response.getRawContent(), this.requestPacket.getAddress(), this.requestPacket.getPort());
                System.out.println("    Sent on port " + port);
                sender.close();
                break;
            } catch (Exception e) {
                if (e.getClass() != BindException.class) {
                    e.printStackTrace();
                    break;
                }
                port++;
                System.out.println("Current port busy, change to port " + port);
            }
        }
    }

    private void handleRegisterReq() {
        byte[] header = this.request.getHeader();
        byte[] body;
        header[MessageConfig.MESSAGE_TYPE_POSITION_IN_HEADER] = LeitsystemResponse.TYPE_NORMAL;
        String name = new String(this.request.getBody()).trim();

        Vehicle newVehicle = new Vehicle(name);
        newVehicle.setIP(this.requestPacket.getAddress());
        newVehicle.setPort((short) this.requestPacket.getPort());
        newVehicle.setId(this.vehicleDatabaseDAO.getAll().size());
        if (this.vehicleDatabaseDAO.getAll().contains(newVehicle) == false) {
            this.vehicleDatabaseDAO.save(newVehicle);
            body = new byte[]{(byte) newVehicle.getId()};
        } else {
            body = new byte[]{(byte) (this.vehicleDatabaseDAO.getAll().indexOf(newVehicle) + 1)};
        }

        this.response = new LeitsystemResponse(header, body);
        logResponse();
        this.vehicleDatabaseDAO.printAll();
    }

    private boolean handleCarStateReq() {
        boolean sendResponseLater = true;
        Trafficsystem trafficsystem = new Trafficsystem(
                CrossroadList.Crossroad_A,
                CrossroadList.Crossroad_B,
                CrossroadList.Crossroad_C
        );
        trafficsystem.setVehicleList(this.vehicleDatabaseDAO);

        byte carId = this.request.getHeader()[MessageConfig.CLIENT_DEVICE_ID_POSITION_IN_HEADER];
        byte carPostion = this.request.getBody()[MessageConfig.VERHICLE_LOCATION_POSITION_IN_BODY];
        byte carDirection = this.request.getBody()[MessageConfig.VERHICLE_DIRECTION_POSITION_IN_BODY];
        byte carSpeed = this.request.getBody()[MessageConfig.VERHICLE_SPEED_POSITION_IN_BODY];

        byte[] header = this.request.getHeader();
        if (this.vehicleDatabaseDAO.get(carId) == null) {
            System.out.println("Sending Reject Response");
            header[MessageConfig.MESSAGE_TYPE_POSITION_IN_HEADER] = LeitsystemResponse.TYPE_REJECT;
            this.response = new LeitsystemResponse(header);
            logResponse();
        } else if (carId == 0) {
            System.out.println("Error: ID 0 is reserved for server");
            sendResponseLater = false;
        } else {
            Vehicle vehicle = this.vehicleDatabaseDAO.get(carId);
            System.out.println("Found Vehicle: " + vehicle.getId() + "." +
                    vehicle.getName() + " - IP: " + vehicle.getIP());

            header[MessageConfig.MESSAGE_TYPE_POSITION_IN_HEADER] = LeitsystemResponse.TYPE_NORMAL;
            byte clearance = trafficsystem.Process_vehicle_status(carId, carPostion, carDirection, carSpeed);
            byte[] body = new byte[]{clearance};
            this.response = new LeitsystemResponse(header, body);
            logResponse();
        }
        return sendResponseLater;
    }

    public void setVehicleDatabaseDAO(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
    }

    @Override
    public void run() {
        handleRequest();
    }

    // Log function
    private void logRequest() {
        System.out.println("Request: " + DataFormatUtils.byteArrToHEXCharList(this.request.getHeader())
                + DataFormatUtils.byteArrToHEXCharList(this.request.getBody()));
    }

    private void logResponse() {
        System.out.println("Response: " + DataFormatUtils.byteArrToHEXCharList(this.response.getHeader())
                + DataFormatUtils.byteArrToHEXCharList(this.response.getBody()));

    }
}
