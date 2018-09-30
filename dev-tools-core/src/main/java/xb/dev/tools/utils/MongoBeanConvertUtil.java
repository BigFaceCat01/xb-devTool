package xb.dev.tools.utils;

import com.mongodb.BasicDBObject;

import java.lang.reflect.Field;

/**
 * @author Created by huangxb
 * @date 2018-09-28 13:59:16
 */
public class MongoBeanConvertUtil {

    private MongoBeanConvertUtil(){}

    public BasicDBObject convert(Class<?> source){

        Field[] fields = source.getDeclaredFields();
        for(Field f:fields){

        }
        return null;
    }
}
