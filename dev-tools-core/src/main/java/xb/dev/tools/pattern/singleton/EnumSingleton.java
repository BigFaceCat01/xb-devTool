package xb.dev.tools.pattern.singleton;

/**
 * @Author: Created by huangxb on 2018-07-14 10:55:46
 * @Description: 枚举
 */
public enum EnumSingleton {
    INSTANCE;
    public void single(){
        System.out.println("single");
    }
}
