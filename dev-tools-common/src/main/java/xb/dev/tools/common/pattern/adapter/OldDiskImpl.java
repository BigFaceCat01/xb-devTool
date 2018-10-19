package xb.dev.tools.common.pattern.adapter;

/**
 * @Author: Created by huangxb on 2018-07-14 10:25:02
 * @Description:
 */
public class OldDiskImpl implements OldDisk {

    private static final String OLD_TYPE = "POWER-1.0";

    public String power() {
        return "type:"+OLD_TYPE;
    }
}
