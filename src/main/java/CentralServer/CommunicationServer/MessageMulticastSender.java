package CentralServer.CommunicationServer;

import com.y4n.UDP.UDPMulticastSender;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MessageMulticastSender extends Thread {
    private UDPMulticastSender sender;

    public MessageMulticastSender() throws IOException {
        this.sender = new UDPMulticastSender(8081);
    }

    public MessageMulticastSender(int port) throws IOException {
        this.sender = new UDPMulticastSender(port);
    }


    @Override
    public void run() {
        try {
            while (true){
                System.out.println("sending hello");
                sender.sendPacket("hello from server");
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
