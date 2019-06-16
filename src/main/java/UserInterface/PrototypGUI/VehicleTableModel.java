package UserInterface.PrototypGUI;

import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import UserInterface.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VehicleTableModel extends AbstractTableModel {
    String[] columnNames = {"ID",
            "Name",
            "Position",
            "Richtung",
            "Geschwindigkeit in cm/s",
            "Freigabe"};
    private List<VehicleDTO> vehicles;
    private VehicleDatabaseDAO vehicleDatabaseDAO;

    public VehicleTableModel(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
        this.vehicles = new ArrayList<VehicleDTO>();
        this.updateTableModel();
    }

    protected void updateTableModel() {
        for (Vehicle vehicleDAO :
                this.vehicleDatabaseDAO.getAll()) {
            if (vehicleDAO.getId() == 0) continue;
            VehicleDTO vehicle = new VehicleDTO(vehicleDAO);
            if (this.vehicles.contains(vehicle) == false) this.vehicles.add(vehicle);
            else {
                int index = this.vehicles.indexOf(vehicle);
                this.vehicles.set(index, vehicle);
            }
        }
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.vehicles.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VehicleDTO vehicle = vehicles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return vehicle.getId();
            case 1:
                return vehicle.getName();
            case 2:
                return vehicle.getPosition();
            case 3:
                return vehicle.getDirection();
            case 4:
                return vehicle.getSpeed();
            case 5:
                return vehicle.isDrivePermission();
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return this.columnNames[col];
    }
}
