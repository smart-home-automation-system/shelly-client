package cloud.cholewa.shelly.http;

/**
 * An RPC call a Gen2+ device refused and said why: {@code code} and {@code rpcMessage} are
 * those of its error - {@code -105} "Argument 'id', value 9 not found!" for a component the
 * device does not have, {@code 404} "No handler for ..." for a method it does not know.
 */
public class ShellyRpcException extends ShellyCallException {

    private final int code;
    private final String rpcMessage;

    public ShellyRpcException(String message, Integer httpStatus, int code, String rpcMessage) {
        super(message, httpStatus, null);
        this.code = code;
        this.rpcMessage = rpcMessage;
    }

    public int code() {
        return code;
    }

    public String rpcMessage() {
        return rpcMessage;
    }
}
