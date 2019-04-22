package Message;

import com.y4n.Utils.MessageUtils.Request;

public class LeitsystemRequest extends Request {
    public LeitsystemRequest(byte[] rawContent) {
        super(rawContent);
        this.setHeaderLength(MessageConfig.MESSAGE_HEADER_LENGTH);
    }

    public LeitsystemRequest(byte[] header, byte[] body) {
        super(header, body);
    }
}
