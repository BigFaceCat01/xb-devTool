package xb.dev.document.temp;

import lombok.Getter;

/**
 * @Author: Created by huangxb on 2018-08-23 16:46:16
 * @Description:
 */
@Getter
public class GoodsProp {
    //参数名称（英文）
    private String propEn;
    //必填可选
    private Byte mustFill;
    //参数类型
    private Byte propType;
    //参数值（中文）
    private String propValueEn;
    //规格类型（英文）
    private String specsEn;
    //规格值（英文）
    private String specsValueEn;

    public void setPropEn(String propEn) {
        this.propEn = propEn;
    }

    public void setMustFill(String mustFill) {
        if(mustFill!=null&&!"".equals(mustFill.trim())){
            if("可选".equals(mustFill)){
                this.mustFill = 1;
            }
            if("必填".equals(mustFill)){
                this.mustFill = 0;
            }
        }

    }

    public void setPropType(String propType) {
        if(propType!=null&&!"".equals(propType.trim())){
            if("单选".equals(propType)){
                this.propType = 1;
            }
            if("多选".equals(propType)){
                this.propType = 2;
            }
        }
    }

    public void setPropValueEn(String propValueEn) {
        this.propValueEn = propValueEn;
    }

    public void setSpecsEn(String specsEn) {
        this.specsEn = specsEn;
    }

    public void setSpecsValueEn(String specsValueEn) {
        this.specsValueEn = specsValueEn;
    }
}
