package TrafficSystemLogic;

public class Area {
    protected int index;
    protected byte [] fields;

    Area(){
        this.index = 0;
        this.fields = new byte []{0,0,0,0};
    }

    Area(int position, byte field0, byte field1, byte field2, byte field3){
        this.index = position;
        this.fields = new byte []{field0, field1, field2, field3};
    }

    public int getindex() {
        return index;
    }

    public byte getfields(int index) {
        return fields[index];
    }

    public byte[] getfields() {
        return fields;
    }

    public void setfields(byte[] fields) {
        this.fields = fields;
    }

    public void setfields(int index, byte field) {
        this.fields[index] = field;
    }

    public void setindex(int index) {
        this.index = index;
    }
    public void raise_index(int amount){
        this.index=((amount+index)%4);
    }
}
