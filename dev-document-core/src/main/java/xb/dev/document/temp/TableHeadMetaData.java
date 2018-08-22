package xb.dev.document.temp;

import java.text.SimpleDateFormat;

/**
 *
 */
public class TableHeadMetaData {
    //表头名称
    private TableHead tableHead;
    //表头所在位置行索引
    private int locationRowIndex=-1;
    //表头
    private int locationColIndex=-1;
    //该属性值类型
    private String dataType;
    //该属性在对象中定义的名称
    private String defName;
    private SimpleDateFormat simpleDateFormat;

    public TableHeadMetaData() {
    }

    public TableHeadMetaData(TableHead tableHead, int locationRowIndex, int locationColIndex,String dataType,String defName) {
        this.tableHead = tableHead;
        this.locationRowIndex = locationRowIndex;
        this.locationColIndex = locationColIndex;
        this.dataType=dataType;
        this.defName=defName;
    }

    public TableHead getTableHead() {
        return tableHead;
    }

    public void setTableHead(TableHead tableHead) {
        this.tableHead = tableHead;
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

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }
}
