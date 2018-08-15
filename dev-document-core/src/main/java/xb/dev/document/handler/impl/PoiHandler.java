package xb.dev.document.handler.impl;


import com.zhongfei.data.document.annotation.Document;
import com.zhongfei.data.document.common.ErrorType;
import com.zhongfei.data.document.common.StringTool;
import com.zhongfei.data.document.config.ConfigurationData;
import com.zhongfei.data.document.exception.DocumentHandlerException;
import com.zhongfei.data.document.exception.HeadNotFoundException;
import com.zhongfei.data.document.handler.DocumentHandler;
import com.zhongfei.data.document.model.HeadMetaData;
import com.zhongfei.data.document.node.Node;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Created by huangxb on 2018-07-27 11:27:30
 * @Description: poi处理文档，针对表格处理
 */
public class PoiHandler extends DocumentHandler {
    /**
     * 根节点
     */
    private Node rootNode = new PoiNode();
    /**
     * 第一个工作表
     */
    private static final int FIRST_SHEET = 0;

    /**
     * 所有单元格
     */
    private List<Cell> allCell = new ArrayList<>();

    /**
     * 所有节点
     */
    private List<Node> allNode = new ArrayList<>();

    /**
     * 存放同一层级的node节点
     */
    private Map<String,List<Node>> nodeMap = new HashMap<>();
    /**
     * 用于输出为文档的数据
     */
    private List data;
    /**
     * poi解析后的excel工作簿对象
     */
    private Workbook wb = null;

    /**
     * 当前
     */
    private int currentLoc = 0;

    private int totalData = 0;

    public PoiHandler() {
    }

    public PoiHandler(List data,ConfigurationData configurationData) {
        this.data = data;
        this.configurationData = configurationData;
    }

    public PoiHandler(String path, InputStream is, ConfigurationData configurationData) throws DocumentHandlerException {
        super(path, is,configurationData);
        build();
    }

    /**
     * 构建各自的文档对象
     */
    private void build() throws DocumentHandlerException {
        //如果不为空，则表示已经构建过，则不进行构建
        if(StringTool.isNotBlank(wb)){
            return;
        }
        InputStream pois = null;
        try {
            //否则构建文档对象
            //如果流对象不为空，则直接使用流对象进行构建
            if (StringTool.isNotBlank(is)) {
                pois = POIFSFileSystem.createNonClosingInputStream(is);
                wb = WorkbookFactory.create(pois);
                //获得头信息
                findHead();
                return;
            }
            //如果路径不为空，则使用本地文件输入流进行构建
            if (StringTool.isNotBlank(path)) {
                is = new FileInputStream(path);
                pois = POIFSFileSystem.createNonClosingInputStream(is);
                wb = WorkbookFactory.create(pois);
                //获得头信息
                findHead();
                return;
            }
            //如果都为空，则抛出异常
            throw new DocumentHandlerException(ErrorType.DOCUMENT_NOT_EXIST.getMsg());
        }catch (Exception e){
            //直接抛出异常
            throw new DocumentHandlerException(e.getMessage(),e);
        }finally {
            //关闭资源
            try {
                if(StringTool.isNotBlank(is)){
                    is.close();
                }
                if(StringTool.isNotBlank(pois)){
                    pois.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void findHead() throws DocumentHandlerException {
        //获得所有单元格列表
        buildAllCell();
        List<HeadMetaData> heads = configurationData.getHeads();
        int headSize = heads.size();
        int headCount = 0;
        int len = allCell.size();
        for(int i = 0;i<len ; i++){
            //取出单元格
            Cell c = allCell.get(i);
            for(HeadMetaData head:heads) {
                //得到单元格数据
                Object cellValue = getHeadCellValue(c);
                //如果单元格对应表头，则进行位置赋值
                if(compareString(cellValue,head.getHead().name())) {
                    configurationData.addLocation(head.getDefName(),c.getColumnIndex());
                    //用于判断是否所有头信息已找到
                    headCount++;
                    //跳出该循环
                    break;
                }
            }
            //头信息全部找到则退出循环
            if(headCount == headSize){
                currentLoc = allCell.get(i).getRowIndex()+1;
                break;
            }
            //如果最后一个单元格也未找到头信息，则抛出异常
            if(i >= (len-1)){
                throw new HeadNotFoundException(ErrorType.HEAD_LOCAT_FIELD.getMsg());
            }
        }
    }

    /**
     * 比较对象s1是否包含s2,若有任何一个为null，则返回false
     * @param s1 应该是string对象，使用object是为了判空
     * @param s2 应该是string对象，使用object是为了判空
     * @return
     */
    private boolean compareString(Object s1,Object s2){
        if(StringTool.isBlank(s1)||StringTool.isBlank(s2)){
            return false;
        }
        return s1.toString().indexOf(s2.toString()) != -1;
    }

    /**
     * 建立所有单元格列表
     */
    private void buildAllCell(){
        //获得第一个工作表
        Sheet sheet =wb.getSheetAt(FIRST_SHEET);
        //循环获得所有单元格
        Iterator<Row> row =sheet.rowIterator();
        while(row.hasNext()){
            Row pr = row.next();
            Iterator<Cell> cc = pr.cellIterator();
            while(cc.hasNext()){
                Cell c = cc.next();
                allCell.add(c);
                allNode.add(new PoiNode(c));
            }
            totalData++;
        }
    }


    @Override
    public Node getRootNode() {
        return new PoiNode();
    }

    /**
     * 获得
     * @return
     */
    @Override
    public List<Node> getNextNodes(){
        Set<String> fieldSet = configurationData.getHeadLocationMap().keySet();
        Sheet sheet =wb.getSheetAt(FIRST_SHEET);
        Row row = sheet.getRow(currentLoc);
        List<Node> nodes = new ArrayList<>();
        for(String field:fieldSet){
            Cell c = row.getCell(configurationData.getLocation(field));
            nodes.add(new PoiNode(c,null,field));
        }
        currentLoc++;
        return nodes;
    }

    /**
     * 获得头信息
     * @param c
     * @return
     */
    private String getHeadCellValue(Cell c){
        //防止空指针异常
        if(StringTool.isBlank(c)){
            return null;
        }
        if(c.getCellType() == HSSFCell.CELL_TYPE_STRING){
            return c.getStringCellValue();
        }else {
            return null;
        }
    }

    /**
     * 获得单元格的值
     * @param c 单元格
     * @param fieldType 单元格对应的
     * @param dateFormat
     * @return
     */
    private Object getCellValue(Cell c,String fieldType,String dateFormat){
        //防止空指针异常
        if(StringTool.isBlank(c)){
            if("string".equalsIgnoreCase(fieldType)){
                return "";
            }
            return null;
        }

        Object cellValue=null;
        //获得单元格数据
        try {
            if("date".equalsIgnoreCase(fieldType)) {
                if(c.getCellType()==HSSFCell.CELL_TYPE_STRING) {
                    cellValue = new SimpleDateFormat(dateFormat).parse(c.getStringCellValue());
                    return cellValue;
                }else{
                    cellValue = c.getDateCellValue();
                    return cellValue;
                }
            }
            //设置单元格数据类型为string
            c.setCellType(HSSFCell.CELL_TYPE_STRING);
            //获得单元格的值
            cellValue=c.getStringCellValue();
        }catch (Exception e){
            //日志记录
            logger.error("cause:"+e.getMessage()+" at:\n row = "+c.getRowIndex()+"column = "+c.getColumnIndex(),e);
        }
        return cellValue;
    }

    @Override
    public boolean hasNext() {return currentLoc == totalData ? false : true; }

    class PoiNode extends Node{
        //层级
        private String field;
        //该node的源对象
        private Object origin;

        public PoiNode(Object origin,Node child,String field) {
            this.origin = origin;
            this.child = child;
            this.field = field;
        }

        public PoiNode(Object origin) {
            this.origin = origin;
        }

        public PoiNode() {
        }

        @Override
        public Object getData() {
            Cell c = (Cell) origin;
            HeadMetaData headMetaData = configurationData.getByFieldName(field);
            Document document = configurationData.getDocument();
            Object value = getCellValue(c,headMetaData.getDataType(),document.dateFormat());
            if (headMetaData.getDataType().equalsIgnoreCase("double")) {//double类型转换
                value = Double.valueOf((String)value);
            }else if(headMetaData.getDataType().equalsIgnoreCase("byte")){//byte类型转换
                value=Byte.valueOf((String)value);
            }else if(headMetaData.getDataType().equalsIgnoreCase("boolean")){//boolean类型转换
                value=Boolean.valueOf((String)value);
            }else if(headMetaData.getDataType().equalsIgnoreCase("BigDecimal")){//BigDecimal类型转换
                double d=Double.valueOf((String)value);
                value=new BigDecimal(d);
            }
            return value;
        }

        @Override
        public String belongField() {
            return field;
        }
    }

    @Override
    public InputStream outputDocument() throws DocumentHandlerException{
        int size = data.size();
        List<HeadMetaData> headMetaData = configurationData.getHeads();
        try {
            //创建工作簿
            HSSFWorkbook workBook = new HSSFWorkbook();
            //创建工作表sheet
            HSSFSheet sheet = workBook.createSheet("sheet");
            for (int i = 0; i <= size; i++) {
                HSSFRow row = sheet.createRow(i);
                int cellIndex = 0;
                Object obj = data.get(i == 0 ? 0 : (i - 1));
                for (HeadMetaData head : headMetaData) {
                    HSSFCell c = row.createCell(cellIndex);
                    if (i == 0) {
                        c.setCellValue(head.getHead().name());
                    } else {
                        String v = getValueFromObject(obj, head.getDefName());
                         c.setCellValue(v);
                    }
                    cellIndex++;
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workBook.write(baos);
            workBook.close();//最后记得关闭工作簿
            return new ByteArrayInputStream(baos.toByteArray());
        }catch (Exception e){
            logger.error("cause:"+e.getMessage(),e);
            throw new DocumentHandlerException("cause:"+e.getMessage(),e);
        }
    }

    private String getValueFromObject(Object obj,String field) throws DocumentHandlerException{
        Class<?> target = configurationData.getTarget();

        try {
            Field f = target.getDeclaredField(field);
            f.setAccessible(true);
            Object value = f.get(obj);
            if(f.getType().getSimpleName().equalsIgnoreCase("date")){
                return new SimpleDateFormat(configurationData.getDocument().dateFormat()).format(value);
            }
            return String.valueOf(value);
        } catch (Exception e) {
            logger.error("cause"+e.getMessage(),e);
            throw new DocumentHandlerException("cause"+e.getMessage(),e);
        }
    }
}
