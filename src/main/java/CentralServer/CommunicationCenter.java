package CentralServer;

import com.y4n.Utils.NetworkUtils;

import java.io.IOException;

public class CommunicationCenter {
    private MessageListener listener;
    private MessageSender sender;
    private InfoServer infoSender;

    public CommunicationCenter() throws IOException {
        this.listener = new MessageListener();
        this.sender = new MessageSender();
        this.infoSender =new InfoServer();
    }

    public void startServer(){
        NetworkUtils.printLocalMachineAddresses();
        this.listener.start();
        this.sender.start();
        //this.infoSender.start();
    }
}
