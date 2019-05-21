package Client;

import TrafficSystemLogic.Area;
import TrafficSystemLogic.Crossroad;
import com.y4n.Utils.DataFormatUtils;
import Message.MessageConfig;

import java.util.List;

public class Vehicle extends Client {
    private String name;
    protected byte position;
    protected byte direction;
    protected byte speed;

    protected byte clearance;

    protected Crossroad Crossroad_current;

    protected int priority;

    protected Area Area_to_book;
    protected Area Area_booked;

    protected byte last_parked= 0;

    public Vehicle(String name) {
        this.name = name;
        Area_to_book = new Area();
        Area_booked = new Area();
    }

    public Vehicle(byte id) {
        this.setId(id);
    }

    public String getName() {
        return this.name;
    }

    public void setStatus(byte position, byte direction, byte speed) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
    }

    public void setClearance(byte clearance) {
        this.clearance = clearance;
    }

    public void setCrossroad_current(Crossroad crossroad_current) {
        Crossroad_current = crossroad_current;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setArea_to_book(Area area_to_book) {
        Area_to_book = area_to_book;
    }

    public void setArea_booked(Area area_booked) {
        Area_booked = area_booked;
    }

    public byte getPosition() {
        return this.position;
    }

    public byte getDirection() {
        return this.direction;
    }

    public byte getSpeed() {
        return this.speed;
    }

    public byte getClearance() {
        return this.clearance;
    }

    public Crossroad getCrossroad_current() {
        return this.Crossroad_current;
    }

    public int getPriority() {
        return this.priority;
    }

    public Area getArea_to_book() {
        return this.Area_to_book;
    }

    public Area getArea_booked() {
        return this.Area_booked;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Vehicle compared = (Vehicle) obj;
        return this.name.equals(compared.name) && this.getIP().equals(compared.getIP());
    }

    public int determine_crossroad(Crossroad Crossroad_A, Crossroad Crossroad_B, Crossroad Crossroad_C) {
        if ((this.position & 0x0F) < 4) {
            switch (this.position & 0xF0) {
                case 160:
                    this.Crossroad_current = Crossroad_A;
                    return 1;

                case 176:
                    this.Crossroad_current = Crossroad_B;
                    return 1;

                case 192:
                    this.Crossroad_current = Crossroad_C;
                    return 1;

                default:
                    System.out.println("Unregistered position, Crossroad can't be determined");
                    return 0;
            }
        } else {
            switch (this.position & 0xF0) {
                case 160:
                    this.Crossroad_current = Crossroad_A;
                    return 1;

                case 176:
                    this.Crossroad_current = Crossroad_B;
                    return 1;

                case 192:
                    this.Crossroad_current = Crossroad_C;
                    return 1;

                default:
                    System.out.println("Unregistered position, Crossroad can't be determined");
                    return 0;
            }
        }
    }

    /*public void determine_priority() {
        switch (this.position & 0xF0) {
            case 160:
                switch (this.position & 0x0F) {
                    case 0:
                        if (direction == 0x10) {
                            this.priority = 1;
                        } else {
                            this.priority = 2;
                        }
                        break;
                    case 1:
                        if (direction == 0x10) {
                            this.priority = 3;
                        } else {
                            this.priority = 4;
                        }
                        break;
                    case 2:
                        if (direction == 0x10) {
                            this.priority = 1;
                        } else {
                            this.priority = 2;
                        }
                        break;
                    case 3:
                        if (direction == 0x10) {
                            this.priority = 3;
                        } else {
                            this.priority = 4;
                        }
                        break;
                }
                break;
            case 176:
                switch (this.position & 0x0F) {
                    case 0:
                        if (direction == 0x10) {
                            this.priority = 3;
                        } else {
                            this.priority = 4;
                        }
                        break;
                    case 1:
                        if (direction == 0x10) {
                            this.priority = 1;
                        } else {
                            this.priority = 2;
                        }
                        break;
                    case 2:
                        if (direction == 0x10) {
                            this.priority = 3;
                        } else {
                            this.priority = 4;
                        }
                        break;
                    case 3:
                        System.out.println("Unregistered position, priority can't be determined");
                        break;
                }
                break;
            case 192:
                switch (this.position & 0x0F) {
                    case 0:
                        if (direction == 0x10) {
                            this.priority = 1;
                        } else {
                            this.priority = 2;
                        }
                        break;
                    case 1:
                        if (direction == 0x10) {
                            this.priority = 3;
                        } else {
                            this.priority = 4;
                        }
                        break;
                    case 2:
                        System.out.println("Unregistered position, priority can't be determined");
                        break;
                    case 3:
                        if (direction == 0x10) {
                            this.priority = 3;
                        } else {
                            this.priority = 4;
                        }
                        break;
                }
                break;
        }
    }
*/
    public void determine_Area() {
        Area_to_book.setIndex(this.position & 0x0F);
        Area_to_book.setField(Area_to_book.getIndex(), (byte) 0x01);
        if (this.direction == (byte) 0x00||this.direction==(byte) 0x10) {
            Area_to_book.raiseIndex(1);
            Area_to_book.setField(Area_to_book.getIndex(), (byte) 0x01);
        }
        if (this.direction == (byte) 0x10) {
            Area_to_book.raiseIndex(1);
            Area_to_book.setField(Area_to_book.getIndex(), (byte) 0x01);
        }
    }

    public void reset_Area(){
        this.Area_to_book.setField(0,(byte)0x00);
        this.Area_to_book.setField(1,(byte)0x00);
        this.Area_to_book.setField(2,(byte)0x00);
        this.Area_to_book.setField(3,(byte)0x00);
    }

    public boolean book_Area() {
        if ((this.position & 0x0F) < 4) {
            return Crossroad_current.Occupie_Area( this.Area_to_book);
        } else {
            return false;
        }
    }

    public void clear_Area() {
        Crossroad_current.Clear_Area(this.Area_to_book);
    }

    @Override
    public String toString() {
        List<String> literalStatus = this.getLiteralStatus();
        return "Vehicle: " + this.name + "(ID: " + this.getId() + ")" +
                "\n\tStatus:" +
                "\n\t\tPosition: " + literalStatus.get(MessageConfig.VEHICLE_LOCATION_POSITION_IN_BODY) +
                "\n\t\tDirection: " + literalStatus.get(MessageConfig.VEHICLE_DIRECTION_POSITION_IN_BODY) +
                "\n\t\tSpeed: " + literalStatus.get(MessageConfig.VEHICLE_SPEED_POSITION_IN_BODY) +
                "\n\t\tDrive Permission: " + this.clearance+
                "\n\t\tCrossroad_current: " + this.getCrossroad_current().getName()+
        "\n\t\tArea_to_book: " + this.Area_to_book.getField(0)+ this.Area_to_book.getField(1)+ this.Area_to_book.getField(2)+ this.Area_to_book.getField(3);
    }

    public List<String> getLiteralStatus() {
        byte[] status = new byte[]{this.position};
        List<String> literalStatus = DataFormatUtils.byteArrToHEXCharList(status);

        if (this.direction == 0x01) {
            literalStatus.add("Rechts");
        } else if (this.direction == 0x10) {
            literalStatus.add("Links");
        } else if (this.direction == 0x00) {
            literalStatus.add("Geradeaus");
        } else {
            literalStatus.add("Error");
        }

        literalStatus.add(Integer.toString(this.speed));
        return literalStatus;
    }
    public void park_Vehicle(){
        this.getCrossroad_current().setArea_parked(this.name, this.position&0x0F);
        this.last_parked = this.position;
    }
    public void depark_Vehicle(){
        if (this.getCrossroad_current().getArea_parked().getName(this.position&0x0F)==this.getName())
        this.getCrossroad_current().setArea_parked("0",this.last_parked&0x0F);
    }
}
