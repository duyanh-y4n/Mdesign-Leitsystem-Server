package CentralServer;

import Message.Enum.RequestID;
import Message.Enum.ResponseID;
import Message.LeitsystemRequest;
import Message.LeitsystemResponse;
import Message.MessageConfig;
import com.y4n.Utils.DataFormatUtils;

public class LeitsystemRequestHandler extends Thread {
    private LeitsystemRequest request;
    private LeitsystemResponse response;

    public LeitsystemRequestHandler(LeitsystemRequest request) {
        this.request = request;
    }

    public void handleRequest() {
        byte requestIDCode = this.request.getHeader()[MessageConfig.MESSAGE_ID_POSITION_IN_HEADER];
        RequestID requestID = RequestID.values()[requestIDCode];

        //TODO: response will be parse here (Logic of the system)
        switch (requestID) {
            case NONE:
                System.out.println(ResponseID.NONE);
                break;
            case REGISTER_REQ:
                System.out.println(ResponseID.REGISTER_ID_RES);
                break;
            case UPDATE_CAR_STATE_REQ:
                System.out.println(ResponseID.DRIVE_PERMISSTION_RES);
                break;
            default:
                System.out.println("none of Request ID was found!");
                break;
        }
    }

    public void sendResponse() {
        this.response = new LeitsystemResponse("Response".getBytes());
        System.out.println(DataFormatUtils.byteArrToStr(this.response.getRawContent()));
    }

    @Override
    public void run() {
        handleRequest();
    }
}
