package xb.dev.tools.common.spring.impl;

import xb.dev.tools.common.spring.BeanDefinition;

/**
 * @author create by huang xiao bao
 * @date 2019-01-14 21:44:34
 */
public class DefaultBeanDefinition implements BeanDefinition {
    private Class<?> clazz;
    private String beanFactoryName;
    private String createBeanMethodName;
    private String staticCreateBeanMethodName;
    private String beanInitMethodName;
    private String beanDestoryMethodName;
    private boolean isSingleton;

    public void setSingleton(boolean singleton){
        isSingleton = singleton;
    }
    @Override
    public Class<?> getBeanClass() {
        return this.clazz;
    }

    @Override
    public String getBeanFactory() {
        return this.beanFactoryName;
    }

    @Override
    public String getCreateBeanMethod() {
        return this.createBeanMethodName;
    }

    @Override
    public String getStaticCreateBeanMethod() {
        return this.staticCreateBeanMethodName;
    }

    @Override
    public String getBeanInitMethod() {
        return this.beanInitMethodName;
    }

    @Override
    public String getScope() {
        return this.isSingleton ? BeanDefinition.SINGLETON : BeanDefinition.PROTOTYPE;
    }

    @Override
    public boolean isSingleton() {
        return this.isSingleton;
    }

    @Override
    public boolean isPrototype() {
        return !this.isSingleton;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
