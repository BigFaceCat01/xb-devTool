package xb.dev.tools.common.spring.impl;

import org.junit.Assert;
import xb.dev.tools.common.spring.BeanDefinition;
import xb.dev.tools.common.spring.BeanDefinitionRegistery;
import xb.dev.tools.common.spring.BeanFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author create by huang xiao bao
 * @date 2019-01-14 21:56:24
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistery, Closeable {
    private Map<String, BeanDefinition> bdMap = new ConcurrentHashMap<>();
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();
    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void register(BeanDefinition bd, String beanName) {
        Assert.assertNotNull("BeanDefinition不能为空",bd);
        Assert.assertNotNull("beanName不能为空",beanName);
        if(bdMap.containsKey(beanName)){
            System.out.println("["+beanName+"]"+"已经存在");
        }
        if(!bd.validate()){
            System.out.println("BeanDefinition不合法");
        }
        if(!bdMap.containsKey(beanName)){
            bdMap.put(beanName,bd);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return bdMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return bdMap.containsKey(beanName);
    }
}
