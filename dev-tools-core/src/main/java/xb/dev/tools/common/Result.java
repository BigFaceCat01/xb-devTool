package xb.dev.tools.common;

import java.io.Serializable;

/**
 * @Author: Created by huangxb on 2018-08-02 15:27:34
 * @Description: 结果类，用于包装返回数据
 */
public class Result<E> implements Serializable {

    /** 状态码 */
    private String code;
    /** 消息信息 */
    private String msg;
    /** 请求数据 */
    private E data;

    public Result() {
    }

    private Result(String code, String msg, E data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public static <E> Result<E> build(String code) {
        return new Result<>(code, null, null);
    }

    public static <E> Result<E> build(String code, E data) {
        return new Result<>(code, null, data);
    }

    public static <E> Result<E> build(String code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <E> Result<E> build(String code, String msg, E data) {
        return new Result<>(code, msg, data);
    }
}