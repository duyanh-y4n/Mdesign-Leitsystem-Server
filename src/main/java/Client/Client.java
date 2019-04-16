package Client;

import java.net.InetAddress;

public class Client {
    private int id;
    private InetAddress IP;

    public Client() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InetAddress getIP() {
        return this.IP;
    }

    public void setIP(InetAddress IP) {
        this.IP = IP;
    }

    public byte[] getIPAsBytesArr(){
        byte[] address = this.IP.getAddress();
        return address;
    }
}
