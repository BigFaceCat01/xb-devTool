package xb.dev.tools.jpa.config;

import com.alibaba.fastjson.JSON;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-22 15:58:30
 */
@Component
public class ObjectKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        String json = JSON.toJSONString(objects[0]);
        return json;
    }
}
