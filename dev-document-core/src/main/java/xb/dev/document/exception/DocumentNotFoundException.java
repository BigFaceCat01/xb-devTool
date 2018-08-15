package xb.dev.document.exception;

import xb.dev.document.common.ErrorType;

/**
 * @Author: Created by huangxb on 2018-07-27 10:15:11
 * @Description:
 */
public class DocumentNotFoundException extends DocumentHandlerException {
    public DocumentNotFoundException() {
        this(ErrorType.DOCUMENT_NOT_FOUND.getMsg());
    }

    public DocumentNotFoundException(String message) {
        super(message);
    }

    public DocumentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
