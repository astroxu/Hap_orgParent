package hap_org.core.map.exception;

public class eBaseException extends Exception {
    private int code;
    private String value;

    public eBaseException(String message, int code, String value) {
        super(message);
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
