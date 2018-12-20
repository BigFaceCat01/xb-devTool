package xb.dev.document.model;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-19 09:59:06
 */
public class MallGoodsProp {
    /**
     * 属性中文
     */
    private String propZh;
    /**
     * 属性英文
     */
    private String propEn;
    /**
     * 属性类型：1普通属性，2销售文字属性，3销售图片属性
     */
    private Byte propType;
    /**
     *是否必选：0不必选，1必选
     */
    private Byte required;
    /**
     * 输入类型：0单选，1多选，2输入
     */
    private Byte inputType;
    /**
     * 是否导航属性（必须是单选或多选）：0不是，1是
     */
    private Byte nav;
    /**
     * 状态：0禁用，1启用
     */
    private Byte status = 1;
    /**
     * 属性值中文
     */
    private String[] propValueZh;
    /**
     * 属性值英文
     */
    private String[] propValueEn;

    public String getPropZh() {
        return propZh;
    }

    public void setPropZh(String propZh) {
        this.propZh = propZh;
    }

    public String getPropEn() {
        return propEn;
    }

    public void setPropEn(String propEn) {
        this.propEn = propEn;
    }

    public Byte getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        if("普通属性".equals(propType.trim())){
            this.propType = 1;
        }else if("销售属性（文字）".equals(propType.trim())){
            this.propType = 2;
        }else if("销售属性（图片）".equals(propType.trim())){
            this.propType = 3;
        }
    }

    public Byte getRequired() {
        return required;
    }

    public void setRequired(String required) {
        if("必填".equals(required.trim())){
            this.required = 1;
        }else if("非必填".equals(required.trim())){
            this.required = 0;
        }
    }

    public Byte getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        if("输入".equals(inputType.trim())){
            this.inputType = 2;
        }else if("多选".equals(inputType.trim())){
            this.inputType = 1;
        }else if("单选".equals(inputType.trim())){
            this.inputType = 0;
        }
    }

    public Byte getNav() {
        return nav;
    }

    public void setNav(String nav) {
        if(nav == null){
            this.nav = 0;
            return;
        }
        if("".equals(nav.trim())){
            this.nav = 0;
        }else if("可导航".equals(nav.trim())){
            this.nav = 1;
        }
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String[] getPropValueZh() {
        return propValueZh;
    }

    public void setPropValueZh(String[] propValueZh) {
        this.propValueZh = propValueZh;
    }

    public String[] getPropValueEn() {
        return propValueEn;
    }

    public void setPropValueEn(String[] propValueEn) {
        this.propValueEn = propValueEn;
    }
}
