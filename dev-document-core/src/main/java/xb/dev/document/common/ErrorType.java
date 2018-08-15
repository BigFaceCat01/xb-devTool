package xb.dev.document.common;

/**
 * @Author: Created by huangxb on 2018-07-27 10:49:39
 * @Description: 错误类型
 */
public enum  ErrorType {
    HEAD_LOCAT_FIELD("头信息定位失败"),
        HEAD_NOT_FOUND("cause:头信息未找到"),
    DATA_PARSE_FAILED("cause:数据读取异常"),
    DOCUMENT_NOT_FOUND("cause:文档注解未找到"),
    DOCUMENT_NOT_EXIST("cause:未发现任何可以创建文档的流对象或者文件路径");

        private String msg;

        private ErrorType(String msg){
            this.msg=msg;
        }
        public String getMsg(){
            return msg;
        }
}
