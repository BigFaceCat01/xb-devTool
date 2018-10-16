package xb.dev.tools.constant;

/**
 * @author Created by huangxb
 * @date 2018-10-16 17:07:30
 */
public enum News163CategoryEnum {

    /**
     * 国内
     */
    GUO_NEI((byte)1,"cm_guonei"),

    /**
     * 航空
     */
    HANG_KONG((byte)2,"cm_guonei");

    /**
     * 网易新闻目录类型id
     */
    private Byte type;
    /**
     * 网易新闻目录名称
     */
    private String name;

    News163CategoryEnum(Byte type,String name){
        this.type = type;
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(Byte type) {
        for(News163CategoryEnum n : News163CategoryEnum.values()){
            if(n.getType().equals(type)){
                return n.getName();
            }
        }
        return null;
    }

}
