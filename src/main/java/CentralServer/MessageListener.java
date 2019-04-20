package CentralServer;

import com.y4n.UDP.UDPUnicast;
import com.y4n.Utils.MessageUtils.RequestTypes;

import java.io.IOException;
import java.net.DatagramPacket;

public class MessageListener extends Thread {
    private UDPUnicast listener;

    public MessageListener() throws IOException {
        this.listener = new UDPUnicast();
    }

    @Override
    public void run() {
        try {
            while (true) {
                DatagramPacket packet = this.listener.getPacket(20);
                if (packetIsFromLeitsystemClient(packet)) {
                    System.out.println("Request from Leitsystem Client at: " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return this.listener.getPort();
    }

    private boolean packetIsFromLeitsystemClient(DatagramPacket packet) {
        byte[] leitsystemRequestType = {RequestTypes.CREATE, RequestTypes.UPDATE, RequestTypes.READ, RequestTypes.DELETE};
        short requestTypePosInHeader = 0;
        for (int i = 0; i < leitsystemRequestType.length; i++) {
            if (packet.getData()[requestTypePosInHeader] == leitsystemRequestType[i]) {
                return true;
            }
        }
        return false;
    }
}
