package Client;

import com.y4n.Utils.DataFormatUtils;
import com.y4n.Utils.MessageUtils.Request;

import java.net.InetAddress;

public class Client {
    private int id;
    private InetAddress IP;
    private short port;
    private Request lastRequest;

    public Client() {
    }

    public Request getLastRequest() {
        return this.lastRequest;
    }

    public void setLastRequest(Request lastRequest) {
        this.lastRequest = lastRequest;
    }

    public short getPort() {
        return this.port;
    }

    public void setPort(short port) {
        this.port = port;
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
        return DataFormatUtils.getShortNumAsBytes(this.port);
    }

    //TODO: write test
}
