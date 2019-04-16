package CentralServer;

import com.y4n.UDP.UDPMulticastSender;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InfoServer extends Thread{
    private UDPMulticastSender sender;

    public InfoServer() throws IOException {
        this.sender = new UDPMulticastSender(8081);
    }

    @Override
    public void run() {
        try {
            while (true){
                System.out.println("sending public message");
                sender.sendPacket("public Info from server");
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
