package xb.dev.tools.mybatis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Created by huangxb on 2018-08-08 14:43:38
 * @Description:
 */
public class DateUtil {

    public static String format(Date date,String format){
        return new SimpleDateFormat(format).format(date);
    }


    static class Inner{
        private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    }
}
