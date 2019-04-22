package CentralServer.CommunicationServer;

import CentralServer.ServerConfig;
import com.y4n.Utils.NetworkUtils;

import java.io.IOException;

public class CommunicationCenter {
    private MessageListener messageListener;
    private MessageMulticastSender sender;
    private InfoServer infoSender;

    public CommunicationCenter() throws IOException {
        this.messageListener = new MessageListener(ServerConfig.LISTENER_PORT);
        this.sender = new MessageMulticastSender(ServerConfig.MULTICAST_SENDER_PORT);
        this.infoSender = new InfoServer(ServerConfig.MULTICAST_SENDER_PORT);
        this.infoSender.setMessageListenerPort(this.messageListener.getPort());
    }

    public void startServer() {
        NetworkUtils.printLocalMachineAddresses();
        this.messageListener.start();
//        this.sender.start();
        this.infoSender.start();
    }
}