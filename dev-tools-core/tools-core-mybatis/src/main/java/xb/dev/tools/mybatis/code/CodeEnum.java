package xb.dev.tools.mybatis.code;

/**
 * @author Created by huangxb on 2018-08-08 17:56:00
 * 规范错误发生时错误代码返回
 */
public enum CodeEnum {
    /*************公共错误****************/
    /**
     * 操作失败
     */
    FAILED("-1", "操作失败", ""),
    /**
     * 操作成功
     */
    SUCCESS("0", "操作成功", ""),
    /**
     * 没有数据
     */
    NO_DATA("1", "没有数据", ""),
    /**
     * 内部异常
     */
    SYS_ERROR("2", "系统异常", ""),
    /**
     * 重复提交
     */
    RESUBMIT("100", "重复提交", ""),
    /**
     * 请求参数错误
     */
    PARAMS_ERROR("101", "请求参数错误", ""),

    /*************自定义错误****************/

    USER_ACCOUNT_USERNAME_OR_PASSWORD_WRONG("20101","用户名或密码错误","user name or password error");

    /**
     *错误码
     */
    private String code;
    /**
     *错误代码中文描述
     */
    private String chDesc;
    /**
     *错误码英文描述
     */
    private String enDesc;

    CodeEnum(String code, String chDesc, String enDesc) {
        this.code = code;
        this.chDesc = chDesc;
        this.enDesc = enDesc;
    }

    public String getCode() {
        return code;
    }

    public String getChDesc() {
        return chDesc;
    }

    public String getEnDesc() {
        return enDesc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setChDesc(String chDesc) {
        this.chDesc = chDesc;
    }

    public void setEnDesc(String enDesc) {
        this.enDesc = enDesc;
    }
}
