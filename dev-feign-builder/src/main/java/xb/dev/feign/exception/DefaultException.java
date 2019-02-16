package xb.dev.feign.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-15 13:57:47
 */
public class DefaultException extends RuntimeException {

    public DefaultException() {
        this("unknown exception occured");
    }

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(String message){
        super(message);
    }
}
