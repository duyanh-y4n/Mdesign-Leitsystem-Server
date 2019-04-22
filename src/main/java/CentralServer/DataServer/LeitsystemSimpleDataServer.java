package CentralServer.DataServer;

import Client.*;

import java.util.ArrayList;
import java.util.List;

public class LeitsystemSimpleDataServer implements DataServer {
    private List<Car> carList;

    public LeitsystemSimpleDataServer() {
        this.carList = new ArrayList<Car>();
    }

    @Override
    public void createCar(Car client) {
        this.carList.add(client);
    }

    @Override
    public void printCarList() {
        for (Car car :
                this.carList) {
            System.out.println(car.getId() + "." + car.getName() + " - IP: " + car.getIP());
        }
    }

    @Override
    public List<Car> getCarList() {
        return this.carList;
    }
}
