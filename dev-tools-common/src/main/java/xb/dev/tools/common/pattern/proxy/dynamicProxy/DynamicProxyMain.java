package xb.dev.tools.common.pattern.proxy.dynamicProxy;


import xb.dev.tools.common.pattern.proxy.HelloWorld;
import xb.dev.tools.common.pattern.proxy.HelloWorldImpl;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {
    public static void main(String[] args){
        HelloWorld helloWorld=new HelloWorldImpl();
       HelloWorld helloWorld1=(HelloWorld) Proxy.newProxyInstance(helloWorld.getClass().getClassLoader(),helloWorld.getClass().getInterfaces(),new HelloWorldInvocationHandler(helloWorld));
       helloWorld1.sayHello();
    }
}
