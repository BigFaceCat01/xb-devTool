package xb.dev.document.temp;

/**
 * 表格解析异常类
 */
public class ExcelParseException extends Exception {
    public ExcelParseException() {
    }

    public ExcelParseException(String message) {
        super(message);
    }

    public ExcelParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelParseException(Throwable cause) {
        super(cause);
    }
}
