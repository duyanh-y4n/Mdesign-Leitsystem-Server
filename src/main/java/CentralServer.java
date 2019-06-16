import CommunicationServer.CommunicationCenter;
import DataServer.VehicleDatabaseDAO;
import UserInterface.UserInterface;

public class CentralServer {
    private VehicleDatabaseDAO vehicleDatabaseDAO;
    private CommunicationCenter communicationCenter;
    private UserInterface UI;
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

    public void setUI(UserInterface ui) {
        this.UI = ui;
        this.communicationCenter.setUI(this.UI);
    }
}
