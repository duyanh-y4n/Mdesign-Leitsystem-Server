package Client;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Random;

public class ClientTest {
    @BeforeClass
    public static void createDummies() {

    }

    @Test
    public void getterSetterTest() throws UnknownHostException {
        Client client = new Client();
        byte[] rawIP = new byte[]{(byte) 192, (byte) 168, (byte) 1, (byte) 1};
        InetAddress IP = InetAddress.getByAddress(rawIP);
        Random random = new Random();
        int ranID = random.nextInt(100);
        short port = (short) random.nextInt(65535);

        //Setter
        client.setId(ranID);
        client.setIP(IP);
        client.setPort(port);

        //Getter
        Assert.assertEquals(IP, client.getIP());
        Assert.assertEquals(ranID, client.getId());
        Assert.assertEquals(port, client.getPort());
    }

    @Test
    public void isShouldReturnCorrectBytesCodeFromPort() {
        Client client = new Client();
        client.setPort((short) 8080);
        byte[] port_in_bytes = new byte[]{(byte) 0x1F, (byte) 0x90};
        byte[] clientRawPort = client.getPortNumAsBytes();
        Assert.assertArrayEquals(port_in_bytes, clientRawPort);
        ByteBuffer buffer = ByteBuffer.wrap(port_in_bytes);
        short num = buffer.getShort();
        Assert.assertEquals(num, 8080);
    }
}
