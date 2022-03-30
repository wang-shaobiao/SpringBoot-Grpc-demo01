import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtobufUtils {

    public static <T> T jsonToPf(String json, Message.Builder builder) {
        if (builder == null) {
            return null;
        }
        JsonFormat
    }
}
