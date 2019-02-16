package xb.dev.tools.common.spring;

/**
 * bean 注册
 * @author create by huang xiao bao
 * @date 2019-01-14 21:41:49
 */
public interface BeanDefinitionRegistery {
    void register(BeanDefinition bd,String beanName);
    BeanDefinition getBeanDefinition(String beanName);
    boolean containsBeanDefinition(String beanName);
}
