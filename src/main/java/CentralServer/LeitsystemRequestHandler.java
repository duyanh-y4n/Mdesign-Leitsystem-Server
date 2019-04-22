package CentralServer;

import Message.LeitsystemRequest;
import Message.LeitsystemResponse;

public class LeitsystemRequestHandler {
    public static LeitsystemResponse handleRequest(LeitsystemRequest request){
        return new LeitsystemResponse("Response".getBytes());
    }
}
