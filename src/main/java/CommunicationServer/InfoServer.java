package CommunicationServer;

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

    public InfoServer(int port) throws IOException {
        this.sender = new UDPMulticastSender(port);
    }

    //save the info from Listenport of this whole server to send it later to all client
    public void setMessageListenerPort(int messageListenerPort) {
        this.messageListenerPort = messageListenerPort;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] ServerInfo = this.getIPAndPortAsPacketInBytes();
                sender.sendPacket(ServerInfo);
                TimeUnit.SECONDS.sleep(ServerConfig.SERVER_INFO_SENDING_INTERVAL);
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
