import CentralServer.CentralServer;
import CentralServer.CommunicationServer.CommunicationCenter;
import CentralServer.DataServer.LeitsystemSimpleVerhicleDatabaseDAO;
import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import com.y4n.Utils.NetworkUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        CentralServer server = new CentralServer();
        CommunicationCenter communicationCenter = new CommunicationCenter();
        VehicleDatabaseDAO vehicleDatabaseDAO = new LeitsystemSimpleVerhicleDatabaseDAO();
        vehicleDatabaseDAO.save(new Vehicle("Reserved"));
        server.setCommunicationCenter(communicationCenter);
        server.setVehicleDatabaseDAO(vehicleDatabaseDAO);
        server.startServer();
    }
}
