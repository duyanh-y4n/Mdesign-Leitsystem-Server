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
import UserInterface.UserInterface;
import com.y4n.Utils.DataFormatUtils;

import java.net.BindException;
import java.net.DatagramPacket;

public class LeitsystemRequestHandler extends Thread {
    private DatagramPacket requestPacket;
    private LeitsystemRequest request;
    private LeitsystemResponse response;
    private VehicleDatabaseDAO vehicleDatabaseDAO;
    private UserInterface UI;

    public void setUI(UserInterface UI) {
        this.UI = UI;
    }

    public LeitsystemRequestHandler(DatagramPacket requestPacket) {
        this.requestPacket = requestPacket;
        this.request = new LeitsystemRequest(requestPacket.getData());
        this.request.setHeaderLength(MessageConfig.MESSAGE_HEADER_LENGTH);
    }

    public void handleRequest() throws InterruptedException {
        byte requestIDCode = this.request.getHeader()[MessageConfig.MESSAGE_ID_POSITION_IN_HEADER];
        RequestID requestID = RequestID.values()[requestIDCode];

        //TODO: response will be parse here (Logic of the system)
        switch (requestID) {
            case NONE:
                System.out.println("    " + ResponseID.NONE + " to " + requestID + ":");
                logRequest();
                break;
            case REGISTER_REQ:
                System.out.println("    " + ResponseID.REGISTER_ID_RES + " to " + requestID + ":");
                logRequest();
                handleRegisterReq();
                sendResponse();
                break;
            case UPDATE_CAR_STATE_REQ:
                System.out.println("    " + ResponseID.DRIVE_PERMISSTION_RES + " to " + requestID + ":");
                logRequest();
                if (handleCarStateReq()) sendResponse();
                break;
            default:
                System.out.println("none of Request ID was found!");
                break;
        }
        this.UI.updateData();
    }

    private void sendResponse() {
        int port = ServerConfig.UNICAST_SENDER_PORT;
        while (true) {
            try {
                MessageUnicastSender sender = new MessageUnicastSender(port);
                sender.send(this.response.getRawContent(), this.requestPacket.getAddress(), this.requestPacket.getPort());
                Vehicle vehicle = this.vehicleDatabaseDAO.get(
                        this.response.getHeader()[MessageConfig.CLIENT_DEVICE_ID_POSITION_IN_HEADER]
                );
                System.out.println("    Response sent on port " + port + " to " + vehicle.getName()
                        + " at " + vehicle.getIP());
                sender.close();
                break;
            } catch (Exception e) {
                if (e.getClass() != BindException.class) {
                    e.printStackTrace();
                    break;
                }
                port++;
                System.out.println("    Current port busy, change to port " + port);
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
            System.out.println("New Vehicle - sending back new ID!");
            this.vehicleDatabaseDAO.save(newVehicle);
            body = new byte[]{(byte) newVehicle.getId()};
            header[MessageConfig.CLIENT_DEVICE_ID_POSITION_IN_HEADER] = (byte) newVehicle.getId();
        } else {
            System.out.println("Vehicle already registered - sending back ID");
            body = new byte[]{(byte) (this.vehicleDatabaseDAO.getAll().indexOf(newVehicle))};
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
        byte carPostion = this.request.getBody()[MessageConfig.VEHICLE_LOCATION_POSITION_IN_BODY];
        byte carDirection = this.request.getBody()[MessageConfig.VEHICLE_DIRECTION_POSITION_IN_BODY];
        byte carSpeed = this.request.getBody()[MessageConfig.VEHICLE_SPEED_POSITION_IN_BODY];

        byte[] header = this.request.getHeader();
        if (this.vehicleDatabaseDAO.get(carId) == null) {
            //TODO: handle case unregisted car sending message
//            System.out.println("Sending Reject Response");
//            header[MessageConfig.MESSAGE_TYPE_POSITION_IN_HEADER] = LeitsystemResponse.TYPE_REJECT;
//            this.response = new LeitsystemResponse(header);
//            logResponse();

            System.out.println("Reject Request");
            sendResponseLater = false;
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
            logCarState(vehicle);
        }
        return sendResponseLater;
    }

    public void setVehicleDatabaseDAO(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Log function
    private void logRequest() {
        System.out.println("    Request: " + DataFormatUtils.byteArrToHEXCharList(this.request.getHeader())
                + DataFormatUtils.byteArrToHEXCharList(this.request.getBody()));
    }

    private void logResponse() {
        System.out.println("    Response: " + DataFormatUtils.byteArrToHEXCharList(this.response.getHeader())
                + DataFormatUtils.byteArrToHEXCharList(this.response.getBody()));
    }

    private void logCarState(Vehicle vehicle){
        System.out.println("Newly updated vehicle");
        System.out.println(vehicle);
    }
}
