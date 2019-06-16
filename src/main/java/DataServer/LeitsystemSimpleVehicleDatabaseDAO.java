package DataServer;

import Client.*;

import java.util.ArrayList;
import java.util.List;

public class LeitsystemSimpleVehicleDatabaseDAO extends VehicleDatabaseDAO {

    public LeitsystemSimpleVehicleDatabaseDAO() {
        this.vehicleList = new ArrayList<Vehicle>();
    }

    public List<Vehicle> getVehicleList() {
        return this.vehicleList;
    }

    @Override
    public void update(Vehicle vehicle, String[] params) {

    }

    @Override
    public void printAll() {
        System.out.println("Client List:");
        for (Vehicle vehicle :
                this.vehicleList) {
            System.out.println(vehicle.getId() + "." + vehicle.getName() + " - IP: " + vehicle.getIP() + ":"
                    + vehicle.getPort()
            );
        }
    }
}
