package xb.dev.tools.pattern.proxy.staticProxy;


import xb.dev.tools.pattern.proxy.HelloWorld;
import xb.dev.tools.pattern.proxy.HelloWorldImpl;

public class StaticProxyMain {
    public static void main(String[] args){
        HelloWorld helloWorldStaticProxy=new HelloWorldStaticProxy(new HelloWorldImpl());
        helloWorldStaticProxy.sayHello();
    }
}
