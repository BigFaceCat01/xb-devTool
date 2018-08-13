package xb.dev.tools.pattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloWorldInvocationHandler implements InvocationHandler {
    private Object target;

    public HelloWorldInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result=method.invoke(target,args);
        System.out.println("after");
        return result;
    }
}
