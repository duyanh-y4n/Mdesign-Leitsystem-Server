package CentralServer.DataServer;

import Client.Car;

import java.util.List;

public interface DataServer {
    public abstract void createCar(Car client);
    public abstract void printCarList();
    public abstract List<Car> getCarList();
}
