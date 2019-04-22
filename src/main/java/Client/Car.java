package Client;

public class Car extends Client{
    private String name;

    public Car(String name) {
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
        Car compared = (Car) obj;
        return this.name.equals(compared.name)&&this.getIP().equals(compared.getIP());
    }
}
