import CentralServer.CentralServer;
import CentralServer.CommunicationServer.CommunicationCenter;
import CentralServer.DataServer.LeitsystemSimpleVerhicleDatabaseDAO;
import CentralServer.DataServer.VehicleDatabaseDAO;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        CentralServer server = new CentralServer();
        CommunicationCenter communicationCenter = new CommunicationCenter();
        VehicleDatabaseDAO vehicleDatabaseDAO = new LeitsystemSimpleVerhicleDatabaseDAO();
        server.setCommunicationCenter(communicationCenter);
        server.setVehicleDatabaseDAO(vehicleDatabaseDAO);
        server.startServer();
    }
}
