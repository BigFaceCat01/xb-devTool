package xb.dev.document.model;

import com.zhongfei.data.document.annotation.Head;

/**
 *
 */
public class HeadMetaData {
    //表头名称
    private Head head;
    //表头所在位置行索引
    private int locationRowIndex=-1;
    //表头
    private int locationColIndex=-1;
    //该属性值类型
    private String dataType;
    //该属性在对象中定义的名称
    private String defName;
    private String dateFormat;

    public HeadMetaData() {
    }

    public HeadMetaData(Head head, int locationRowIndex, int locationColIndex, String dataType, String defName) {
        this.head = head;
        this.locationRowIndex = locationRowIndex;
        this.locationColIndex = locationColIndex;
        this.dataType=dataType;
        this.defName=defName;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public int getLocationRowIndex() {
        return locationRowIndex;
    }

    public void setLocationRowIndex(int locationRowIndex) {
        this.locationRowIndex = locationRowIndex;
    }

    public int getLocationColIndex() {
        return locationColIndex;
    }

    public void setLocationColIndex(int locationColIndex) {
        this.locationColIndex = locationColIndex;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
