package TrafficSystemLogic;

public class Crossroad {
    protected String name;
    protected Area Area_of_positions;
    protected Area Area_occupied;

    Crossroad(String name, byte position0, byte position1, byte position2, byte position3){
        this.name = name;
        this.Area_of_positions = new Area(0, position0, position1, position2, position3);
        this.Area_occupied = new Area();
    }

    public Area getArea_occupied() {
        return Area_occupied;
    }

    public Area getArea_of_positions() {
        return Area_of_positions;
    }

    public String getName() {
        return name;
    }

    public void setArea_occupied(Area area_occupied) {
        Area_occupied = area_occupied;
    }

    public void setArea_of_positions(Area area_of_positions) {
        Area_of_positions = area_of_positions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int Occupie_Area(int priority, Area Area_to_book){
        Area area_temp = new Area();
        int succes = 1;
        for (int i = 0; i<=3; i++){
            if(Area_to_book.getfields(i)==1){
                area_temp.setfields(i,(byte)priority);
            }else if (Area_to_book.getfields(i)==0){
                area_temp.setfields(i,(byte) 0);
            }else{
                System.out.println("Booking error:"+Area_to_book.getfields(i)+"on field "+i);
            }
        }
        for(int i = 0; i<=3; i++){
            if(area_temp.getfields(i)<=Area_occupied.getfields(i) && area_temp.getfields(i)!=0){
                succes = 0;
            }
        }
        if (succes==1){
            for(int i = 0; i<=3; i++){
                if(area_temp.getfields(i)>Area_occupied.getfields(i)){
                    Area_occupied.setfields(i,(byte) area_temp.getfields(i));
                }
            }
        }
        return succes;
    }

    public void Clear_Area(Area Area_to_clear){
        for(int i = 0; i<=3; i++){
            if(Area_to_clear.getfields(i)==1) {
                Area_occupied.setfields(i, (byte) 0);
            }
        }
    }
}

