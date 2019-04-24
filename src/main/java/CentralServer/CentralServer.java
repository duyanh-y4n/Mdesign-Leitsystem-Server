package CentralServer;

import CentralServer.CommunicationServer.CommunicationCenter;
import CentralServer.DataServer.VehicleDatabaseDAO;

public class CentralServer {
    private VehicleDatabaseDAO vehicleDatabaseDAO;
    private CommunicationCenter communicationCenter;

    public CentralServer() {
    }

    public void setVehicleDatabaseDAO(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
        this.communicationCenter.setVehicleDatabaseDAO(this.vehicleDatabaseDAO);
    }

    public void setCommunicationCenter(CommunicationCenter communicationCenter) {
        this.communicationCenter = communicationCenter;
    }

    public void startServer() {
        this.communicationCenter.startServer();
    }
}
