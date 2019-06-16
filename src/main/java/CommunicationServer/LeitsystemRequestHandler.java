package CommunicationServer;

import CommunicationServer.MessageUnicastSender;
import CommunicationServer.ServerConfig;
import DataServer.VehicleDatabaseDAO;
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
        String logMessage;

        switch (requestID) {
            case NONE:
                logMessage = "    " + ResponseID.NONE.toString() + " to " + requestID.toString() + ":";
                log(logMessage);
                logRequest();
                break;
            case REGISTER_REQ:
                logMessage = "    " + ResponseID.REGISTER_ID_RES.toString().toString() + " to " + requestID + ":";
                log(logMessage);
                logRequest();
                handleRegisterReq();
                sendResponse();
                break;
            case UPDATE_CAR_STATE_REQ:
                logMessage = "    " + ResponseID.DRIVE_PERMISSTION_RES.toString() + " to " + requestID.toString() + ":";
                log(logMessage);
                logRequest();
                if (handleCarStateReq()) sendResponse();
                break;
            default:
                log("none of Request ID was found!");
                break;
        }
        this.UI.updateData();
    }

    private void sendResponse() {
        int port = ServerConfig.UNICAST_SENDER_PORT;
        String logMessage;
        MessageUnicastSender sender;
        while (true) {
            try {
                sender = new MessageUnicastSender(port);
                sender.send(this.response.getRawContent(), this.requestPacket.getAddress(), this.requestPacket.getPort());
                Vehicle vehicle = this.vehicleDatabaseDAO.get(
                        this.response.getHeader()[MessageConfig.CLIENT_DEVICE_ID_POSITION_IN_HEADER]
                );
                logMessage = "    Response sent on port " + port + " to " + vehicle.getName()
                        + " at " + vehicle.getIP().toString();
                log(logMessage);
                // TODO: BUG: cant reach this statement, sender wont automatically close on Exception, cause overflow of sender
                // Solution: catch Error when new unhandled Exception happen
                sender.close();
                break;
            } catch (Exception e) {
                if (e.getClass() != BindException.class) {
                    e.printStackTrace();
                    logExceptionStacktrace(e);
                    break;
                }
                port++;
                logMessage = "    Current port busy, change to port " + port;
                log(logMessage);
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
            String logMessage = "New Vehicle - sending back new ID!";
            log(logMessage);
            this.vehicleDatabaseDAO.save(newVehicle);
            body = new byte[]{(byte) newVehicle.getId()};
            header[MessageConfig.CLIENT_DEVICE_ID_POSITION_IN_HEADER] = (byte) newVehicle.getId();
        } else {
            String logMessage = "Vehicle already registered - sending back ID";
            byte registeredID = (byte) this.vehicleDatabaseDAO.getAll().indexOf(newVehicle);
            log(logMessage);
            body = new byte[]{registeredID};
            header[MessageConfig.CLIENT_DEVICE_ID_POSITION_IN_HEADER] = registeredID;
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

            log("Wrong ID! No Vehicle found! Reject Request");
            sendResponseLater = false;
        } else if (carId == 0) {
            log("Error: ID 0 is reserved for server! Reject Request");
            sendResponseLater = false;
        } else {
            Vehicle vehicle = this.vehicleDatabaseDAO.get(carId);
            String logMessage = "Found Vehicle: " + vehicle.getId() + "." +
                    vehicle.getName() + " - IP: " + vehicle.getIP().toString();
            log(logMessage);

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
            logExceptionStacktrace(e);
        }
    }

    // Log function
    private void logRequest() {
        String message = "    Request: " + DataFormatUtils.byteArrToHEXCharList(this.request.getHeader()).toString()
                + DataFormatUtils.byteArrToHEXCharList(this.request.getBody()).toString();
        System.out.println(message);
        this.UI.log(message);
    }

    private void logResponse() {
        String message = "    Response: " + DataFormatUtils.byteArrToHEXCharList(this.response.getHeader()).toString()
                + DataFormatUtils.byteArrToHEXCharList(this.response.getBody()).toString();
        System.out.println(message);
        this.UI.log(message);
    }

    private void logCarState(Vehicle vehicle) {
        String message = "Newly updated vehicle\n" + vehicle.toString();
        System.out.println(message);
        this.UI.log(message);
    }

    private void logExceptionStacktrace(Exception e) {
        String logMessage = e.getClass().getName();
        for (StackTraceElement stackTraceElement :
                e.getStackTrace()) {
            logMessage = logMessage.concat("\nat " + stackTraceElement);
        }
        logMessage = logMessage.concat("\n\n(Server Still running, dont worry)");
        log(logMessage);
    }

    private void log(String logMessage) {
        System.out.println(logMessage);
        this.UI.log(logMessage);
    }
}
