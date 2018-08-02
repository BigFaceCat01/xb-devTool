package xb.dev.tools.pattern.singleton;

/**
 * @Author: Created by huangxb on 2018-07-14 10:58:55
 * @Description: 饿汉式
 */
public class HungerSingleton {

    private HungerSingleton(){}

    private static HungerSingleton hungerSingleton = new HungerSingleton();

    public static HungerSingleton getInstance(){
        return hungerSingleton;
    }
}
