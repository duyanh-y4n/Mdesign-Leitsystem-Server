package UserInterface;

import Client.Vehicle;
import Message.MessageConfig;

import java.util.List;

public class VehicleDTO {
    private int id;
    private String name;
    private String position;
    private String direction;
    private String speed;
    private boolean drivePermission;

    public VehicleDTO(Vehicle vehicle){
        List<String> literalStatus = vehicle.getLiteralStatus();
        this.id = vehicle.getId();
        this.name = vehicle.getName();
        this.drivePermission = vehicle.getClearance()==1?true:false;

        this.position = literalStatus.get(0);
        this.direction = literalStatus.get(1);
        this.speed = literalStatus.get(2);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getPosition() {
        return this.position;
    }

    public String getSpeed() {
        return this.speed;
    }

    public boolean isDrivePermission() {
        return this.drivePermission;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj== null) return false;
        VehicleDTO compared = (VehicleDTO) obj;
        return this.id == compared.id && this.name.equals(compared.name);
    }
}
