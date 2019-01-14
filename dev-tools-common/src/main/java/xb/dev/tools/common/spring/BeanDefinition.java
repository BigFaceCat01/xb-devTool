package xb.dev.tools.common.spring;

/**
 * bean 定义
 * @author create by huang xiao bao
 * @date 2019-01-14 21:35:14
 */
public interface BeanDefinition {
    String SINGLETON = "";
    String PROTOTYPE = "";
    Class<?> getBeanClass();
    String getBeanFactory();
    String getCreateBeanMethod();
    String getStaticCreateBeanMethod();
    String getBeanInitMethod();
    String getScope();
    boolean isSingleton();
    boolean isPrototype();
    boolean validate();
}
