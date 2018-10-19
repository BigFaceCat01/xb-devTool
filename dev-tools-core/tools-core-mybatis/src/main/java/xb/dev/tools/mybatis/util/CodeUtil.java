package xb.dev.tools.mybatis.util;

import java.util.Date;

/**
 * @Author: Created by huangxb on 2018-08-08 14:39:25
 * @Description:
 */
public class CodeUtil {
    private static int auto = 10;

    public static String getNewsId(){
        String s = "XB"+DateUtil.format(new Date(),"yyyyMMddhhmmss")+""+Integer.toBinaryString(auto);
        auto ++;
        return s;
    }
}
