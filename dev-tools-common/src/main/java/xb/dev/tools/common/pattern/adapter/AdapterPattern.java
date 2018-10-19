package xb.dev.tools.common.pattern.adapter;

/**
 * 适配器模式,需要使用新硬盘(NewDisk)，但电源类型是旧的类型(TYPE = OLD_TYPE)
 */
public class AdapterPattern {
    public static void main(String[] args){
        NewDisk newDisk = new NewDiskImpl();
        System.out.println("未适配，使用:"+newDisk.power());

        OldDisk oldDisk = new OldDiskImpl();

        NewDisk adapter = new NewDiskAdapter(oldDisk);
        System.out.println("适配后，使用:"+adapter.power());


    }

}
