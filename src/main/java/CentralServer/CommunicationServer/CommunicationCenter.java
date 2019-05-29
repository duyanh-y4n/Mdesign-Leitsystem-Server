package CentralServer.CommunicationServer;

import CentralServer.DataServer.VehicleDatabaseDAO;
import CentralServer.ServerConfig;
import UserInterface.UserInterface;
import com.y4n.Utils.NetworkUtils;

import java.io.IOException;

public class CommunicationCenter {
    private MessageListener messageListener;
    private InfoServer infoSender;
    private VehicleDatabaseDAO vehicleDatabaseDAO;
    private UserInterface UI;

    public void setUI(UserInterface UI) {
        this.UI = UI;
        this.messageListener.setUI(this.UI);
    }

    public CommunicationCenter() throws IOException {
        this.messageListener = new MessageListener(ServerConfig.LISTENER_PORT);
        this.infoSender = new InfoServer(ServerConfig.MULTICAST_SENDER_PORT);
        this.infoSender.setMessageListenerPort(this.messageListener.getPort());
    }

    public void setVehicleDatabaseDAO(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
        this.messageListener.setVehicleDatabaseDAO(this.vehicleDatabaseDAO);
    }

    public void startServer() {
        this.logServerInfo();
        this.messageListener.start();
        this.infoSender.start();
    }

    private void logServerInfo(){
        String message = "Server info:\n" + NetworkUtils.getLocalHostIP() + "\nServer Ready";
        System.out.println(message);
        this.UI.log(message);
    }
}
