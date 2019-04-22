package CentralServer;

import CentralServer.CommunicationServer.CommunicationCenter;
import CentralServer.DataServer.DataServer;

public class CentralServer {
    private DataServer dataServer;
    private CommunicationCenter communicationCenter;

    public CentralServer() {
    }

    public void setDataServer(DataServer dataServer) {
        this.dataServer = dataServer;
        this.communicationCenter.setDataServer(this.dataServer);
    }

    public void setCommunicationCenter(CommunicationCenter communicationCenter) {
        this.communicationCenter = communicationCenter;
    }

    public void startServer() {
        this.communicationCenter.startServer();
    }
}
