package CentralServer;

import com.y4n.UDP.UDPMulticastSender;
import com.y4n.Utils.DataFormatUtils;
import com.y4n.Utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InfoServer extends Thread {
    private UDPMulticastSender sender;
    private int messageListenerPort;

    public InfoServer() throws IOException {
        this.sender = new UDPMulticastSender(8081);
    }

    public void setMessageListenerPort(int messageListenerPort) {
        this.messageListenerPort = messageListenerPort;
    }

    @Override
    public void run() {
        try {
            while (true) {
//                System.out.println("sending Server IP and Port");
                sender.sendPacket(this.getIPAndPortAsPacketInBytes());
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getIPAndPortAsPacketInBytes() {
        return DataFormatUtils.concatBytesArr(NetworkUtils.getLocalIPAsRawBytes(),
                DataFormatUtils.getShortNumAsBytes((short) this.getMessageListenerPort()));
    }

    private int getMessageListenerPort() {
        return this.messageListenerPort;
    }
}
