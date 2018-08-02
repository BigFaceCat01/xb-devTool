package xb.dev.tools.pattern.adapter;

/**
 * @Author: Created by huangxb on 2018-07-14 10:30:48
 * @Description: 新硬盘适配器
 */
public class NewDiskAdapter implements NewDisk {

    private OldDisk oldDisk;

    public NewDiskAdapter(OldDisk oldDisk) {
        this.oldDisk = oldDisk;
    }

    public String power() {
        return oldDisk.power();
    }
}
