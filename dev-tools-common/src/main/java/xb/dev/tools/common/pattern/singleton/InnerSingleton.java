package xb.dev.tools.common.pattern.singleton;

/**
 * @Author: Created by huangxb on 2018-07-14 11:05:10
 * @Description: 使用类级内部类
 */
public class InnerSingleton {

    private InnerSingleton(){}

    public static InnerSingleton getInstance(){
        return Inner.innerSingleton;
    }

    private static class Inner{
        private static InnerSingleton innerSingleton = new InnerSingleton();
    }
}
