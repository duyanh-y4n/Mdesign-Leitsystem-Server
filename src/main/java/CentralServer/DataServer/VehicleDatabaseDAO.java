package CentralServer.DataServer;

import Client.Vehicle;

import java.util.List;

public abstract class VehicleDatabaseDAO implements BaseDAO<Vehicle> {
    protected List<Vehicle> vehicleList;


    @Override
    public Vehicle get(int id) {
        return this.vehicleList.get(id);
    }

    @Override
    public List<Vehicle> getAll() {
        return this.vehicleList;
    }

    @Override
    public void save(Vehicle vehicle) {
        if(this.vehicleList.contains(vehicle)==false) this.vehicleList.add(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        this.vehicleList.remove(vehicle);
    }
}
