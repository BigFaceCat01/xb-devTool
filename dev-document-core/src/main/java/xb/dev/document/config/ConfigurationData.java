package xb.dev.document.config;

import xb.dev.document.annotation.Document;
import xb.dev.document.annotation.Head;
import xb.dev.document.common.StringTool;
import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.document.exception.DocumentNotFoundException;
import xb.dev.document.exception.HeadNotFoundException;
import xb.dev.document.model.HeadMetaData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by huangxb on 2018-07-27 10:03:13
 * @Description: 配置数据
 */
public class ConfigurationData {
    /**
     * 文档信息
     */
    private Document document;
    /**
     * 类声明
     */
    private Class<?> target;
    /**
     * 数据头信息
     */
    private List<HeadMetaData>  heads = new ArrayList<>();
    /**
     * 数据头信息以map存储形式
     */
    private Map<String,HeadMetaData> headMap = new HashMap<>();

    /**
     * 存储数据头信息的位置信息，这里只存储列信息
     */
    private Map<String,Integer> headLocationMap = new HashMap<>();

    public ConfigurationData(Class<?> target) throws DocumentHandlerException {
        this.target = target;
        readAnnotation(target);
    }

    public ConfigurationData(){
        readProperties();
    }

    private void readAnnotation(Class<?> target) throws DocumentHandlerException {
        //获得document注解信息
        document=target.getAnnotation(Document.class);
        if(StringTool.isBlank(document)){
            throw new DocumentNotFoundException();
        }

        //获得表头信息
        Field[] fields=target.getDeclaredFields();
        for(Field f:fields){

            HeadMetaData headMetaData=new HeadMetaData();
            //该属性声明的注解有没有TableHead注解
            Head head=f.getAnnotation(Head.class);
            if(StringTool.isNotBlank(head)){
                //设置属性名
                headMetaData.setDefName(f.getName());
                //设置数据类型
                headMetaData.setDataType(f.getType().getSimpleName());
                headMetaData.setDateFormat(document.dateFormat());
                headMetaData.setHead(head);
                heads.add(headMetaData);
                headMap.put(f.getName(),headMetaData);
            }
        }
        if(heads.size()==0){
            throw new HeadNotFoundException();
        }
    }

    private void readProperties(){

    }

    public Class<?> getTarget() {
        return target;
    }

    public Document getDocument() {
        return document;
    }

    public List<HeadMetaData> getHeads() {
        return heads;
    }

    /**
     * 通过字段名称，查询该字段对应的头信息
     * @param fieldName
     * @return
     */
    public HeadMetaData getByFieldName(String fieldName){
        return headMap.get(fieldName);
    }

    public void addLocation(String key,Integer loc){
        headLocationMap.put(key,loc);
    }

    public int getLocation(String key){
        Integer  col = headLocationMap.get(key);
        return col == null ? -1 : col;
    }

    public Map<String,Integer> getHeadLocationMap(){
        return headLocationMap;
    }
}
