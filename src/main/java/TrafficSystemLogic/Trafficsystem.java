package TrafficSystemLogic;

import CentralServer.DataServer.LeitsystemSimpleVerhicleDatabaseDAO;
import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Trafficsystem {
    protected Crossroad Crossroad_A = new Crossroad("Crossroad A", (byte) 0xA0, (byte) 0xA1, (byte) 0xA2, (byte) 0xA3);
    protected Crossroad Crossroad_B = new Crossroad("Crossroad B", (byte) 0xB0, (byte) 0xB1, (byte) 0xB2, (byte) 0xB3);
    protected Crossroad Crossroad_C = new Crossroad("Crossroad C", (byte) 0xC0, (byte) 0xC1, (byte) 0xC2, (byte) 0xC3);

    //    protected List Vehicle_list = new ArrayList();
    protected VehicleDatabaseDAO Vehicle_list;
    protected List Vehicle_list_Crossroad_A_idle = new ArrayList();
    protected List Vehicle_list_Crossroad_B_idle = new ArrayList();
    protected List Vehicle_list_Crossroad_C_idle = new ArrayList();

    public void setVehicle_list(VehicleDatabaseDAO vehicle_list) {
        Vehicle_list = vehicle_list;
    }

    public void add_Vehicle(byte id) {
        Vehicle temp = new Vehicle(id);
        Vehicle_list.save(temp);

    }

    public Vehicle acces_Vehicle(int id) {
        return Vehicle_list.get(id);
    }

    public void add_Vehicle_idle(Vehicle Vehicle, Crossroad Crossroad) {
        if (Crossroad.name.equals("Crossroad A"))
            Vehicle_list_Crossroad_A_idle.add(Vehicle);
        else if (Crossroad.name.equals("Crossroad B"))
            Vehicle_list_Crossroad_B_idle.add(Vehicle);
        else if (Crossroad.name.equals("Crossroad C"))
            Vehicle_list_Crossroad_C_idle.add(Vehicle);
        else
            System.out.println("Idle " + Vehicle.getName() + " can not be assigned to the list of " + Crossroad.name + " because the Crossroad is not listed");
    }

    public Vehicle acces_idle_Vehicle(int i, Crossroad Crossroad) {
        if (Crossroad.name.equals("Crossroad A"))
            return (Vehicle) Vehicle_list_Crossroad_A_idle.get(i);
        else if (Crossroad.name.equals("Crossroad B"))
            return (Vehicle) Vehicle_list_Crossroad_B_idle.get(i);
        else if (Crossroad.name.equals("Crossroad A"))
            return (Vehicle) Vehicle_list_Crossroad_C_idle.get(i);
        else
            System.out.println("Idle vehicles can not be accessed because the Crossroad is not listed");
        Vehicle Errorvehicle = new Vehicle((byte) 0xFF);
        return Errorvehicle;

    }

    public byte Process_vehicle_status(byte ID, byte position, byte direction, byte speed) {
        Vehicle Processed_vehicle = acces_Vehicle(ID);
        Processed_vehicle.setStatus(position, direction, speed);
        if (Processed_vehicle.determine_crossroad(Crossroad_A, Crossroad_B, Crossroad_C) == 1) {
            if ((Processed_vehicle.getPosition() & 0x0F) < 4) {
                Processed_vehicle.determine_priority();
                Processed_vehicle.determine_Area();
                if (Processed_vehicle.book_Area() == 1) {
                    Processed_vehicle.setClearance((byte) 0x01);
                    return Processed_vehicle.getClearance();
                } else {
                    Processed_vehicle.setClearance((byte) 0x00);
                    return Processed_vehicle.getClearance();
                }
            } else if ((Processed_vehicle.getPosition() & 0x0F) < 8) {
                Processed_vehicle.clear_Area();
                Processed_vehicle.setClearance((byte) 0x00);
                return Processed_vehicle.getClearance();

            } else {
                System.out.println("Unknown Position on " + Processed_vehicle.getCrossroad_current().getName() + ", vehiclestatus was not processed");
                Processed_vehicle.setClearance((byte) 0x00);
                return Processed_vehicle.getClearance();
            }
        } else {
            System.out.println("Unknown Crossroad, vehiclestatus was not processed");
            Processed_vehicle.setClearance((byte) 0x00);
            return Processed_vehicle.getClearance();
        }
    }

}
