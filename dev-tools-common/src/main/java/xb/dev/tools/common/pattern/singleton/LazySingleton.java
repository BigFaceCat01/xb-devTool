package xb.dev.tools.common.pattern.singleton;

/**
 * @Author: Created by huangxb on 2018-07-14 11:00:56
 * @Description: 懒汉式，双重检验加锁制
 */
public class LazySingleton {

    /** 使用volatile修饰，禁用线程本地缓存，在原地址进行修改，使修改可见    */
    private static volatile LazySingleton lazySingleton = null;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if(lazySingleton == null){
            synchronized (LazySingleton.class) {
                if(lazySingleton == null) {
                    return new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
