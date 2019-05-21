package TrafficSystemLogic;

public class Crossroad {
    protected String name;
    protected Area Area_of_positions;
    protected Area Area_occupied;
    protected Area Area_parked;

    Crossroad(String name, byte position0, byte position1, byte position2, byte position3){
        this.name = name;
        this.Area_of_positions = new Area(0, position0, position1, position2, position3);
        this.Area_occupied = new Area();
        this.Area_parked = new Area();
    }

    public Area getArea_occupied() {
        return Area_occupied;
    }

    public Area getArea_of_positions() {
        return Area_of_positions;
    }

    public Area getArea_parked() {
        return Area_parked;
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

    public void setArea_parked(String name, int index) {
        Area_parked.setName(name,index);
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean Occupie_Area(Area Area_to_book){
        Area area_temp = new Area();
        boolean success = true;
        for (int i = 0; i<=3; i++){
            if(Area_to_book.getField(i)==1){
                area_temp.setField(i,(byte)1);
            }else if (Area_to_book.getField(i)==0){
                area_temp.setField(i,(byte) 0);
            }else{
                System.out.println("Booking error:"+Area_to_book.getField(i)+"on field "+i);
            }
        }
        for(int i = 0; i<=3; i++){
            if(area_temp.getField(i)<=Area_occupied.getField(i) && area_temp.getField(i)!=0){
                success = false;
            }
        }
        if (success){
            for(int i = 0; i<=3; i++){
                if(area_temp.getField(i)>Area_occupied.getField(i)){
                    Area_occupied.setField(i,(byte) area_temp.getField(i));

                }
            }
        }
        return success;
    }

    public void Clear_Area(Area Area_to_clear){
        for(int i = 0; i<=3; i++){
            if(Area_to_clear.getField(i)==1) {
                Area_occupied.setField(i, (byte) 0);
            }
        }
    }
}

