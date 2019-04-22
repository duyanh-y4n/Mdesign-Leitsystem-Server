import CentralServer.CentralServer;
import CentralServer.CommunicationServer.CommunicationCenter;
import CentralServer.DataServer.LeitsystemSimpleDataServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        CentralServer server = new CentralServer();
        server.setCommunicationCenter(new CommunicationCenter());
        server.setDataServer(new LeitsystemSimpleDataServer());
        server.startServer();
    }
}
