package Client;

public class Vehicle extends Client{
    private String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        Vehicle compared = (Vehicle) obj;
        return this.name.equals(compared.name)&&this.getIP().equals(compared.getIP());
    }


}
