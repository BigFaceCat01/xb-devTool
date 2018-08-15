package xb.dev.document.exception;

/**
 * @Author: Created by huangxb on 2018-07-27 10:37:37
 * @Description:
 */
public class DocumentHandlerException extends Exception {
    public DocumentHandlerException() {

    }

    public DocumentHandlerException(String message) {
        super(message);
    }

    public DocumentHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
