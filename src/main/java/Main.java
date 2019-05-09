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
            CentralServer server = new CentralServer();
            CommunicationCenter communicationCenter = new CommunicationCenter();
            VehicleDatabaseDAO vehicleDatabaseDAO = new LeitsystemSimpleVehicleDatabaseDAO();
            vehicleDatabaseDAO.save(new Vehicle("Reserved"));
            server.setCommunicationCenter(communicationCenter);
            server.setVehicleDatabaseDAO(vehicleDatabaseDAO);
            server.startServer();

            TableGraphicUI UI = new TableGraphicUI(vehicleDatabaseDAO);
            communicationCenter.setUI(UI);
            SwingUtilities.invokeLater(UI);
        } catch (Exception e) {
            AlertUtils.alertExceptionStackTrace(e);
        }
    }
}
