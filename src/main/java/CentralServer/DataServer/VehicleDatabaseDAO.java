package CentralServer.DataServer;

import Client.Vehicle;

import java.util.List;

public abstract class VehicleDatabaseDAO implements BaseDAO<Vehicle> {
    protected List<Vehicle> vehicleList;


    //TODO: handle Error when get no id
    @Override
    public Vehicle get(int id) {
        try {
            Vehicle vehicle = this.vehicleList.get(id);
            return vehicle;
        } catch (Exception e) {
            if (e.getClass() == IndexOutOfBoundsException.class) {
                System.out.println("Client not found, please register!");
            } else {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public List<Vehicle> getAll() {
        return this.vehicleList;
    }

    @Override
    public void save(Vehicle vehicle) {
        if (this.vehicleList.contains(vehicle) == false) this.vehicleList.add(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        this.vehicleList.remove(vehicle);
    }
}
