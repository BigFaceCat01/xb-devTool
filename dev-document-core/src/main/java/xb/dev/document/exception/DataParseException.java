package xb.dev.document.exception;

import com.zhongfei.data.document.common.ErrorType;

/**
 * @Author: Created by huangxb on 2018-07-27 09:48:15
 * @Description:
 */
public class DataParseException extends DocumentHandlerException {
    public DataParseException() {
        this(ErrorType.DATA_PARSE_FAILED.getMsg());
    }

    public DataParseException(String message) {
        super(message);
    }

    public DataParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
