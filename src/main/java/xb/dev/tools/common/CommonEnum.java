package xb.dev.tools.common;

/**
 * @Author: Created by huangxb on 2018-08-08 14:54:21
 * @Description:
 */
public enum CommonEnum {
    DATE_FORMAT_ONE("yyyyMMdd"),
    DATE_FORMAT_TWO("yyyy-MM-dd"),
    DATE_FORMAT_THREE("yyyy/MM/dd"),
    DATETIME_FORMAT_ONE("yyyyMMddhhmmss"),
    DATETIME_FORMAT_TWO("yyyy-MM-dd hh:mm:ss"),
    DATETIME_FORMAT_THREE("yyyy/MM/dd hh:mm:ss");;

    private String format;

    private CommonEnum(String format){
        this.format = format;
    }

    public String getFormat(){return format;}
}
