package xb.dev.tools.es.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import xb.dev.tools.common.Result;
import xb.dev.tools.es.code.CodeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by huangxb on 2018-08-02 15:25
 * 全局异常异常类
 */
@RestControllerAdvice
@Component
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //service层抛出XbServiceException异常
    //全部返回http 200
    @ExceptionHandler(XbServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object handle(XbServiceException exception, HttpServletRequest request) {
        CodeEnum codeEnum = getCodeEnum(exception.getCode());
        logger.error("[" + exception.getCode() + "]:" + codeEnum.getChDesc(), exception);
        //返回错误码对应信息，返回前端
        return Result.build(exception.getCode(), exception.getMessage());
    }

    //用户输入参数异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object handle(ConstraintViolationException exception, HttpServletRequest request) {
        logger.error("参数不合法", exception);
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        String more = null;
        for (ConstraintViolation<?> item : violations) {
            logger.info(item.getMessage());
        }
        return Result.build("-1", exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object handle(HttpMessageNotReadableException exception, HttpServletRequest request) {
        logger.error("参数不合法", exception);
        return Result.build("-1", exception.getMessage());
    }

    @ExceptionHandler({InvalidFormatException.class, NumberFormatException.class, UnexpectedTypeException.class,
            MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object paramFormatExceptionHandle(Exception e, HttpServletRequest request) {
        logger.error("参数格式错误", e);
        return Result.build("-1", e.getMessage());
    }

    //未知异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object handle(Exception exception, HttpServletRequest request) {
        logger.error("未知异常", exception);
        return Result.build("-1", exception.getMessage());
    }

//    //根据浏览器语言选择对应版本的说明
//    public static String exceptionCodeDesc(CaCodeEnum caCodeEnum, HttpServletRequest request) {
//
//        String header = request.getHeader("accept-language");
//        /**
//         * @author: update by huangxb on 2018-7-20
//         * @desc: 解决header在某些情况下可能为null的情况
//         * */
//        if(header == null){
//            return caCodeEnum.getChDesc();
//        }
//        String[] headerStr = header.split(",");
//        String desc = "";
//        String language = headerStr[0].substring(0, 2);
//        if ("zh".equals(language)) {
//            return caCodeEnum.getChDesc();
//        } else if ("fr".equals(language)) {
//            return caCodeEnum.getFrDesc();
//        } else if ("en".equals(language)) {
//            return caCodeEnum.getEnDesc();
//        } else { //其他都返回中文
//            return caCodeEnum.getChDesc();
//        }
//    }
//
//    //缓存本地的caCodeEnum
    private static ConcurrentHashMap<String, CodeEnum> codeMap = new ConcurrentHashMap();
//
//    //懒加载
    private static CodeEnum getCodeEnum(String code) {
        CodeEnum codeEnum = codeMap.get(code);
        //没有做null的判断,因为这里的null应该在开发测试阶段被消除,不应出现！！！
        if (codeEnum == null) {
            for (CodeEnum c : CodeEnum.values()) {
                if (c.getCode().equals(code)) {
                    codeMap.put(c.getCode(), c);
                    return c;
                }
            }
            return null;
        } else {
            return codeEnum;
        }
    }
}