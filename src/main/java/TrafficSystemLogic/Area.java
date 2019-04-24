package TrafficSystemLogic;

public class Area {
    protected int index;
    protected byte[] fields;

    public Area() {
        this.index = 0;
        this.fields = new byte[]{0, 0, 0, 0};
    }

    public Area(int position, byte field0, byte field1, byte field2, byte field3) {
        this.index = position;
        this.fields = new byte[]{field0, field1, field2, field3};
    }

    public int getIndex() {
        return index;
    }

    public byte getField(int index) {
        return fields[index];
    }

    public byte[] getFields() {
        return fields;
    }

    public void setFields(byte[] fields) {
        this.fields = fields;
    }

    public void setField(int index, byte field) {
        this.fields[index] = field;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void raiseIndex(int amount) {
        this.index = ((amount + index) % 4);
    }
}
