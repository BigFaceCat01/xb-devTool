package xb.dev.tools.pattern.proxy.staticProxy;


import xb.dev.tools.pattern.proxy.HelloWorld;

public class HelloWorldStaticProxy implements HelloWorld {
    private HelloWorld helloWorld;

    public HelloWorldStaticProxy(){}

    public HelloWorldStaticProxy(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public void sayHello(){
        System.out.println("execute before");
        helloWorld.sayHello();
        System.out.println("execute after");
    }
}
