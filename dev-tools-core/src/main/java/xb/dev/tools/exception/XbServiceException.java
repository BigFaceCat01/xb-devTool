package xb.dev.tools.exception;

/**
 * @Author: Created by huangxb on 2018-08-01 18:21:39
 * @Description: 公共异常类
 */
public class XbServiceException extends RuntimeException{

    private  String code;

    public XbServiceException() {
        this("-1","",null);
    }

    public XbServiceException(String message, Throwable cause) {
        this("-1",message,cause);
    }

    public XbServiceException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
