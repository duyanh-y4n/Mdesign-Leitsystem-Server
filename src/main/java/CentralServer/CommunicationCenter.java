package CentralServer;

import com.y4n.Utils.NetworkUtils;

import java.io.IOException;

public class CommunicationCenter {
    private MessageListener messageListener;
    private MessageSender sender;
    private InfoServer infoSender;

    public CommunicationCenter() throws IOException {
        this.messageListener = new MessageListener();
        this.sender = new MessageSender();
        this.infoSender =new InfoServer();
        this.infoSender.setMessageListenerPort(this.messageListener.getPort());
    }

    public void startServer(){
        NetworkUtils.printLocalMachineAddresses();
        this.messageListener.start();
//        this.sender.start();
        this.infoSender.start();
    }
}
