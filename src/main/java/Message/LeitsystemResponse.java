package Message;

import com.y4n.Utils.MessageUtils.Request;
import com.y4n.Utils.MessageUtils.Response;

public class LeitsystemResponse extends Response {
    public LeitsystemResponse(byte[] rawContent) {
        super(rawContent);
    }

    public LeitsystemResponse(byte[] header, byte[] body) {
        super(header, body);
    }

    public LeitsystemResponse(int ResponseType, Request correspondingRequest) {
        super(ResponseType, correspondingRequest);
    }

    public LeitsystemResponse(Request correspondingRequest) {
        super(correspondingRequest);
    }
}
