package xb.dev.tools.pattern.adapter;

/**
 * @Author: Created by huangxb on 2018-07-14 10:26:44
 * @Description:
 */
public class NewDiskImpl implements NewDisk {

    private static final String NEW_TYPE = "POWER-3.0";

    public String power() {
        return "type:"+NEW_TYPE;
    }
}
