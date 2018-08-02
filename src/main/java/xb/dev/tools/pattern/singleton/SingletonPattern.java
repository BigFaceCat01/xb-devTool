package xb.dev.tools.pattern.singleton;

/**
 * @Author: Created by huangxb on 2018-07-14 10:53:07
 * @Description: 单例模式
 */
public class SingletonPattern {

    public static void main(String[] args){
        /**  枚举方式      */
       EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        /**  饿汉方式      */
       HungerSingleton hungerSingleton = HungerSingleton.getInstance();
        /**  懒汉方式，双重检验加锁制      */
       LazySingleton lazySingleton = LazySingleton.getInstance();
        /**  类级内部类方式      */
       InnerSingleton innerSingleton = InnerSingleton.getInstance();
    }
}
