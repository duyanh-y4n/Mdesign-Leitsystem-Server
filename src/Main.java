import CentralServer.CommunicationCenter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        CommunicationCenter server = new CommunicationCenter();
        server.startServer();
    }
}
