package Message;

import com.y4n.Utils.MessageUtils.Request;
import com.y4n.Utils.MessageUtils.Response;

public class LeitsystemResponse extends Response {
    public static final byte TYPE_CONFIMATION = 0x11;
    public static final byte TYPE_NORMAL = 0x12;
    public static final byte TYPE_REJECT = 0x10;

    public LeitsystemResponse(byte[] rawContent) {
        super(rawContent);
        this.setHeaderLength(MessageConfig.MESSAGE_HEADER_LENGTH);
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
