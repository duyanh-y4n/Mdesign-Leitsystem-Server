import CommunicationServer.CommunicationCenter;
import DataServer.LeitsystemSimpleVehicleDatabaseDAO;
import DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import UserInterface.PrototypGUI.TableGraphicUI;
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
