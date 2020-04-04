package pe.elcomercio.pagoefectivosdk.cip.usermodel;

public class CipError {

    private final int code;
    private final String field;
    private final String message;

    public CipError(int code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
