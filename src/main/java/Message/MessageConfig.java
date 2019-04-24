package Message;

public class MessageConfig {
    public static int MESSAGE_LENGTH = 20;
    public static int MESSAGE_HEADER_LENGTH = 4;
    public static int MESSAGE_BODY_LENGTH = MESSAGE_LENGTH - MESSAGE_HEADER_LENGTH;

    //HEADER description
    public static int MESSAGE_TYPE_POSITION_IN_HEADER = 0;
    public static int CLIENT_DEVICE_TYPE_POSITION_IN_HEADER = 1;
    public static int CLIENT_DEVICE_ID_POSITION_IN_HEADER = 2;
    public static int MESSAGE_ID_POSITION_IN_HEADER = 3;

    //Body description
    public static int VERHICLE_LOCATION_POSITION_IN_BODY = 0;
    public static int VERHICLE_DIRECTION_POSITION_IN_BODY = 1;
    public static int VERHICLE_SPEED_POSITION_IN_BODY = 2;
}
