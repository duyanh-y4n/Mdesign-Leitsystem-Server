package Client;

import java.net.InetAddress;

public class Client {
    private int id;
    private InetAddress IP;
    private short port;

    public short getPort() {
        return this.port;
    }

    public void setPort(short port) {
        this.port = port;
    }

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

    public byte[] getPortNumAsBytes(){
        byte[] data = new byte [2];
        data[1] = (byte) (this.port & 0xFF);
        data[0] = (byte) ((this.port >> 8) & 0xFF);
        return data;
    }

    //TODO: write test
}
