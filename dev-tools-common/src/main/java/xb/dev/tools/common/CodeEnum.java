package xb.dev.tools.common;

/**
 * @Author: Created by huangxb on 2018-08-08 17:56:00
 * @Description: 规范错误发生时错误代码返回
 */
public enum CodeEnum {
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

    //在下方自定义错误码,使用固定规则，例如2(服务级错误(1为系统级错误),1位) 05(服务模块代码,2位) 02(具体错误代码,2位),共5位

    TEST_ERROR("20101","错误码示例",""),
    SUGGEST_NEWS_FAILD("20102","根据关键词获得搜索建议失败","");;

    //错误码
    private String code;
    //错误代码中文描述
    private String chDesc;
    //错误码英文描述
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
}
