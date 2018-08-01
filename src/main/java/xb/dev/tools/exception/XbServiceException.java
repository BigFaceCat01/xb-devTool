package xb.dev.tools.exception;

/**
 * @Author: Created by huangxb on 2018-08-01 18:21:39
 * @Description: 公共异常类
 */
public class XbServiceException extends Exception{
    public XbServiceException() {
    }

    public XbServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
