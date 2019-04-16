package CentralServer;

import com.y4n.UDP.UDPUnicast;

import java.io.IOException;

public class MessageListener extends Thread {
    private UDPUnicast listener;

    public MessageListener() throws IOException {
        this.listener = new UDPUnicast();
    }

    @Override
    public void run() {
        try {
            while (true){
                String message = listener.getPacketBodyAsStr(20);
                if (!message.isEmpty()){
                    System.out.println("Listening...:" + message);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getPort() {
        return this.listener.getPort();
    }
}
