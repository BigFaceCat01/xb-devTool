package xb.dev.document.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于存储excel基础信息
 * @param <T>
 */
public class ExcelMetadata<T> {
    //excel注解
    private Excel excel;
    //excel对应的实体类
    private Class<T> target;
    //表头属性集合
    private List<TableHeadMetaData> thmd=new ArrayList<TableHeadMetaData>();

    public ExcelMetadata() {
    }
    public ExcelMetadata(Class<T> target) {
        this.target = target;
    }

    public ExcelMetadata(Excel excel, Class<T> target, List<TableHeadMetaData> thmd) {
        this.excel = excel;
        this.target = target;
        this.thmd = thmd;
    }

    public Excel getExcel() {
        return excel;
    }

    public void setExcel(Excel excel) {
        this.excel = excel;
    }

    public Class<T> getTarget() {
        return target;
    }

    public void setTarget(Class<T> target) {
        this.target = target;
    }

    public List<TableHeadMetaData> getThmd() {
        return thmd;
    }

    public void setThmd(List<TableHeadMetaData> thmd) {
        this.thmd = thmd;
    }
}
