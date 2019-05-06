import CentralServer.CentralServer;
import CentralServer.CommunicationServer.CommunicationCenter;
import CentralServer.DataServer.LeitsystemSimpleVehicleDatabaseDAO;
import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import UserInterface.TableGraphicUI;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        CentralServer server = new CentralServer();
        CommunicationCenter communicationCenter = new CommunicationCenter();
        VehicleDatabaseDAO vehicleDatabaseDAO = new LeitsystemSimpleVehicleDatabaseDAO();
        vehicleDatabaseDAO.save(new Vehicle("Reserved"));
        server.setCommunicationCenter(communicationCenter);
        server.setVehicleDatabaseDAO(vehicleDatabaseDAO);
        server.startServer();

        TableGraphicUI UI = new TableGraphicUI(vehicleDatabaseDAO);
        SwingUtilities.invokeLater(UI);
    }
}
