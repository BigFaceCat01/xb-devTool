package xb.dev.document.exception;

import com.zhongfei.data.document.common.ErrorType;

/**
 * @Author: Created by huangxb on 2018-07-27 10:32:18
 * @Description:
 */
public class HeadNotFoundException extends DocumentHandlerException {
    public HeadNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeadNotFoundException(String message) {
        super(message);
    }

    public HeadNotFoundException() {
        this(ErrorType.HEAD_NOT_FOUND.getMsg());
    }
}
