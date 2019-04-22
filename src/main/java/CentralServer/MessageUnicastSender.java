package CentralServer;

import com.y4n.UDP.UDPUnicast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MessageUnicastSender {
    private UDPUnicast unicastSender;

    public MessageUnicastSender() throws SocketException, UnknownHostException {
        this.unicastSender = new UDPUnicast(8081);
    }

    public MessageUnicastSender(int port) throws SocketException, UnknownHostException {
        this.unicastSender = new UDPUnicast(port);
    }

    public void send(byte[] message, InetAddress targetIP, int targetPort) throws IOException {
        this.unicastSender.sendPacket(message, targetIP.getHostAddress(), targetPort);
    };

    public void close(){
        this.unicastSender.close();
    }
}
