import CentralServer.CentralServer;
import CentralServer.CommunicationServer.CommunicationCenter;
import CentralServer.DataServer.LeitsystemSimpleVehicleDatabaseDAO;
import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import UserInterface.TableGUI.TableGraphicUI;
import Utils.AlertUtils;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            VehicleDatabaseDAO vehicleDatabaseDAO = new LeitsystemSimpleVehicleDatabaseDAO();
            vehicleDatabaseDAO.save(new Vehicle("Reserved"));
            TableGraphicUI UI = new TableGraphicUI(vehicleDatabaseDAO);

            CentralServer server = new CentralServer();
            CommunicationCenter communicationCenter = new CommunicationCenter();
            communicationCenter.setUI(UI);
            server.setCommunicationCenter(communicationCenter);
            server.setVehicleDatabaseDAO(vehicleDatabaseDAO);
            server.startServer();

            SwingUtilities.invokeLater(UI);
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertExceptionStackTrace(e);
        }
    }
}
