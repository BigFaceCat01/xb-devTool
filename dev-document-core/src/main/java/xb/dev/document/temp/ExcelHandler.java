package xb.dev.document.temp;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格处理类
 * @param <T>
 */
public class ExcelHandler<T> {

    protected final static String NORMAL_TYPE="byte,int,integer,char,character,";
    private Logger logger;
    private ExcelMetadata<T> excelMetadata;
    private Class<T> target;
    private Workbook wb;
    private InputStream is;


    public enum ParseError{

        FileNotFound("文件读取异常"),
        DataFormatError("数据转换异常");

        private String msg;

        private ParseError(String msg){
            this.msg=msg;
        }
        public String getMsg(){
            return msg;
        }
    }

    public ExcelHandler() {

    }

    public ExcelHandler(Class<T> target,String path,Logger logger) throws ExcelParseException{
        try {
            this.is=new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new ExcelParseException(ParseError.FileNotFound.getMsg(),e);
        }
        this.logger=logger;
        this.target = target;
        excelMetadata=new ExcelMetadata<T>(target);
    }
    public ExcelHandler(Class<T> target,InputStream is,Logger logger){
        this.is=is;
        this.logger=logger;
        this.target = target;
        excelMetadata=new ExcelMetadata<T>(target);

    }

    /**
     * 解析开始
     * @return
     * @throws Exception
     */
    public Map<String,List<T>> parse() throws ExcelParseException{

        Map<String, List<T>> resultMap=new HashMap<String, List<T>>();
        try {
            /**
             * 第一步
             * 读取表格记录对应的实体类的注解信息
             */
            List<TableHeadMetaData> tableHeads = getAnotationInfo();
            excelMetadata.setThmd(tableHeads);
            /**
             * 第二步
             * 获得工作簿对象
             */
            InputStream iss = POIFSFileSystem.createNonClosingInputStream(is);
            wb = WorkbookFactory.create(iss);
            //总工作表数
            /**
             * 第三步
             * 根据用户的设置判断哪些工作表需要解析
             */
            int sheetNumber = wb.getNumberOfSheets();
            int start = 0, end = sheetNumber;
            if (excelMetadata.getExcel() != null) {
                //工作表的开始索引应该小于结束索引
                if (excelMetadata.getExcel().sheetFrom() > excelMetadata.getExcel().sheetTo()) {
                    throw new ExcelParseException("sheetFrom must be < sheetTo");
                }
                //根据用户指定的哪些工作表进行解析
                if (excelMetadata.getExcel().sheetFrom() >= 0) {
                    if (excelMetadata.getExcel().sheetTo() >= 0) {
                        start = excelMetadata.getExcel().sheetFrom();
                        end = excelMetadata.getExcel().sheetTo();
                    } else {
                        start = excelMetadata.getExcel().sheetFrom();
                    }
                }
            }
            /**
             * 第四步
             * 解析表格中数据
             */
            for (; start < end; start++) {
                String sheetName = wb.getSheetName(start);
                //读取表头信息
                List<T> dataList = null;
                try {
                    getTableHeadIndexInExcel(start);
                    //读取工作表信息
                    dataList = readDataFromExcel(start);
                    //清除上一个sheet表中表头数据
                    for (TableHeadMetaData tb : excelMetadata.getThmd()) {
                        tb.setLocationColIndex(-1);
                        tb.setLocationRowIndex(-1);
                    }
                } catch (ExcelParseException ex){
                    throw new TableHeadNotFoundException(ex.getMessage(),ex);
                }catch (Exception e) {
                    resultMap.put(sheetName, new ArrayList<>());
                }
                resultMap.put(sheetName, dataList);
            }
        }catch (TableHeadNotFoundException e){
            logger.error("实体对象无TableHead表头注解",e);
            throw new ExcelParseException("实体对象无TableHead表头注解",e);
        }catch (InvalidFormatException | IOException e){
            logger.error("不合法的表格类型，可能该文件不是excel文件",e);
            throw new ExcelParseException("不合法的表格类型",e);
        }
        return resultMap;
    }

    /**
     * 从一个工作表中读取数据
     * getTableHeadIndexInExcel方法获取到了所有表头的行索引列索引，因此设立行偏移rowSkep，
     * 表头行索引加行偏移可以得到下一行表头对应的数据
     * @param sheetIndex
     */
    private List<T> readDataFromExcel(int sheetIndex) throws Exception{
        List<T> lists=new ArrayList<T>();
        //行偏移
        int rowSkep=1;
        //获得指定索引工作表
        Sheet current=wb.getSheetAt(sheetIndex);
        //获得该工作表总行数
        int rowNumber=current.getLastRowNum();
        if(rowNumber<=0){
            return lists;
        }
        //无限循环直到行偏移加上表头行索引大于工作表总行数
        while(true) {
            T instance=target.newInstance();
            boolean flag=true;
            boolean add=true;
            for (TableHeadMetaData tb : excelMetadata.getThmd()) {
                    int rowIndex=tb.getLocationRowIndex()+rowSkep;
                    int cellIndex=tb.getLocationColIndex();

                    if(rowIndex>rowNumber){//如果当前数据行大于总行数
                        //退出无限循环
                        flag=false;
                        //结束当前循环
                        break;
                    };
                    if(current.getRow(rowIndex) == null){
                        add = false;
                        break;
                    }
                    if(current.getRow(rowIndex).getLastCellNum()<0){
                        add=false;
                        break;
                    }
                    Cell c=current.getRow(rowIndex).getCell(cellIndex);
                    setCellValueToObj(c,instance,tb);
            }
            if(!flag){
                break;
            }
            //行偏移自增，读取下一行表格数据
            rowSkep++;
            if(add) {
                lists.add(instance);
            }
        }
        return lists;
    }

    /**
     * 遍历excel获得表头在表格中的索引位置
     */
    private void getTableHeadIndexInExcel(int sheetIndex) throws ExcelParseException{
        //获得表头数量
        int tableHead=excelMetadata.getThmd().size();
        //获得指定索引工作表
        Sheet current=wb.getSheetAt(sheetIndex);
        int tableHeadFindNum=excelMetadata.getThmd().size();
        //获得该工作表总行数
        int rowNumber=current.getLastRowNum();
        if(rowNumber==0){
            return;
        }
        for(int j=0;j<=rowNumber;j++){
            //获得表格行对象
            Row row=current.getRow(j);
            if(row==null){continue;}
            //获得该行包含数据的总单元格数
            int colNumber=row.getLastCellNum();

            for(int k=0;k<=colNumber;k++){
                //循环表头属性
                for(TableHeadMetaData tb:excelMetadata.getThmd()){
                    //判断该列是否已被识别
                    if(tb.getLocationColIndex()>=0||tb.getLocationRowIndex()>=0){
                        //已识别
                        continue;
                    }else{//未被识别
                        Cell c=row.getCell(k);
                        if(c!=null){
                            //设置表格值类型为字符串，便于接下来的取值操作

                                c.setCellType(HSSFCell.CELL_TYPE_STRING);
                                //单元格值
                                String cellValue = c.getStringCellValue().replaceAll("\\s", "");
                                //属性表头
                                String tableHeadValue = tb.getTableHead().name().replaceAll("\\s", "");
                                if (tableHeadValue.equals("")) {
                                    //表头为空，则使用属性名
                                    tableHeadValue = tb.getDefName().replaceAll("\\s", "");
                                }
                                if(cellValue.replaceAll(" ","").indexOf(tableHeadValue.replaceAll(" ",""))>-1){//为真，则该单元格为表头
                                    //修改表头在excel中的索引
                                    tb.setLocationRowIndex(c.getRowIndex());
                                    tb.setLocationColIndex(c.getColumnIndex());
                                    tableHeadFindNum--;
                                    break;
                                }

                        }
                    }
                }
                if(tableHeadFindNum==0){
                    return ;
                }
            }
        }
        String s="";
        for(TableHeadMetaData tb:excelMetadata.getThmd()){
            if(!(tb.getLocationRowIndex()<0)){
                s+=tb.getTableHead().name().equals("")?tb.getDefName():tb.getTableHead().name();
                s+=",";
            }
        }
        if(!(tableHeadFindNum==0)){
            throw new ExcelParseException(s+"表头未找到");
        }
        //
    }

    /**
     * 获得目标实体对象中需要使用的字段
     */
    private List<TableHeadMetaData> getAnotationInfo() throws TableHeadNotFoundException{
        //获得excel注解信息
        Excel excel=target.getAnnotation(Excel.class);
        if(excel!=null){
            excelMetadata.setExcel(excel);
        }

        //获得表头信息
        Field[] fields=target.getDeclaredFields();
        List<TableHeadMetaData> tableHeadMetaDataList=new ArrayList<TableHeadMetaData>();
        for(Field f:fields){

            TableHeadMetaData tableHeadMetaData=new TableHeadMetaData();
            //该属性声明的注解有没有TableHead注解
            TableHead tableHead=f.getAnnotation(TableHead.class);
            if(tableHead!=null){
                //设置属性名
                tableHeadMetaData.setDefName(f.getName());

                //设置数据类型
                tableHeadMetaData.setDataType(f.getType().getSimpleName());
                tableHeadMetaData.setSimpleDateFormat(new SimpleDateFormat(tableHead.dateFormat()));
                tableHeadMetaData.setTableHead(tableHead);
                tableHeadMetaDataList.add(tableHeadMetaData);
            }
        }
        if(tableHeadMetaDataList.size()==0){
            throw new TableHeadNotFoundException("实体类上未发现任何TableHead表头注解");
        }
        return tableHeadMetaDataList;
    }

    /**
     * 将单元格的值设置到对象中
     * @param c 单元格对象
     * @param targetInstance 目标对象
     */
    public void setCellValueToObj(Cell c, Object targetInstance, TableHeadMetaData tb){
        if(c!=null){//判空
            //设置单元格数据类型为string
            String cellValue=null;
            if(!tb.getDataType().equalsIgnoreCase("date")) {
                c.setCellType(HSSFCell.CELL_TYPE_STRING);
                cellValue=c.getStringCellValue();
            }
            //获得单元格数据

            try {
                Object value=cellValue;
                if (tb.getDataType().equalsIgnoreCase("double")) {//double类型转换
                    value = Double.parseDouble(cellValue);
                }else if(tb.getDataType().equalsIgnoreCase("date")){//date类型转换
                    if(c.getCellType()==HSSFCell.CELL_TYPE_STRING) {
                        value = tb.getSimpleDateFormat().parse(c.getStringCellValue());
                    }else{
                        value = c.getDateCellValue();
                    }
                }else if(tb.getDataType().equalsIgnoreCase("byte")){//byte类型转换
                    value=Byte.valueOf(cellValue);
                }else if(tb.getDataType().equalsIgnoreCase("boolean")){//boolean类型转换
                    value=Boolean.valueOf(cellValue);
                }else if(tb.getDataType().equalsIgnoreCase("BigDecimal")){//BigDecimal类型转换
                    double d=Double.valueOf(cellValue);
                    value=new BigDecimal(d);
                }
                //根据定义属性名获得字段
                Field f=target.getDeclaredField(tb.getDefName());
                //设置字段可更改为true，否则如果字段没有使用public修饰无法修改
                f.setAccessible(true);
                //进行赋值
                f.set(targetInstance,value);
            }catch (Exception e){
                //日志记录
                if(logger!=null){
                    logger.error(ParseError.DataFormatError.getMsg()+":"+tb.getDataType(),e);
                }
                //System.err.println("第"+(c.getRowIndex()+1)+"行第"+(c.getColumnIndex()+1)+"列数据读取出错\n"+ ParseError.DataFormatError.getMsg()+":"+tb.getDataType()+"\n"+e.getMessage());

                //继续向下解析
                //throw new ExcelParseException(ParseError.DataFormatError.getMsg()+":"+tb.getDataType(),e);
            }
        }
    }
}
