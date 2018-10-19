package xb.dev.tools.common.pattern.proxy.staticProxy;


import xb.dev.tools.common.pattern.proxy.HelloWorld;
import xb.dev.tools.common.pattern.proxy.HelloWorldImpl;

public class StaticProxyMain {
    public static void main(String[] args){
        HelloWorld helloWorldStaticProxy=new HelloWorldStaticProxy(new HelloWorldImpl());
        helloWorldStaticProxy.sayHello();
    }
}
