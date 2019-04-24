package Client;

import TrafficSystemLogic.Area;
import TrafficSystemLogic.Crossroad;

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
                    return 0;

                case 176:
                    this.Crossroad_current = Crossroad_B;
                    return 0;

                case 192:
                    this.Crossroad_current = Crossroad_C;
                    return 0;

                default:
                    System.out.println("Unregistered position, Crossroad can't be determined");
                    return 0;
            }
        }
    }

    public void determine_priority() {
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

    public void determine_Area() {
        Area_to_book.setindex(this.position & 0x0F);
        Area_to_book.setfields(Area_to_book.getindex(), (byte) 0x01);
        if (this.direction != 0x01) {
            Area_to_book.raise_index(1);
            Area_to_book.setfields(Area_to_book.getindex(), (byte) 0x01);
        }
        if (this.direction == 0x10) {
            Area_to_book.raise_index(1);
            Area_to_book.setfields(Area_to_book.getindex(), (byte) 0x01);
        }
    }

    public int book_Area() {
        if ((this.position & 0x0F) < 4) {
            return Crossroad_current.Occupie_Area(this.priority, this.Area_to_book);
        } else {
            return 0;
        }
    }

    public void clear_Area() {
        Crossroad_current.Clear_Area(this.Area_to_book);
    }
}
