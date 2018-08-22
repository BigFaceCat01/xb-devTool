package xb.dev.document.temp;

public class TableHeadNotFoundException extends Exception {
    public TableHeadNotFoundException() {
    }

    public TableHeadNotFoundException(String message) {
        super(message);
    }

    public TableHeadNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
