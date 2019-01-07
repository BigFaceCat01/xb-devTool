package xb.dev.tools.jpa.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-01 18:15:10
 * @Description: json工具类
 */
public class JsonUtil {
    private JsonUtil(){};
    public static <T> T beanConvert(Object source,Class<T> target){
        String jsonStr = JSON.toJSONString(source);
        return JSON.parseObject(jsonStr, target);
    }
    public static <T> List<T> convertList(List source, Class<T> targetClass) {
        String jsonStr = JSON.toJSONString(source);
        return JSON.parseArray(jsonStr, targetClass);
    }
}
