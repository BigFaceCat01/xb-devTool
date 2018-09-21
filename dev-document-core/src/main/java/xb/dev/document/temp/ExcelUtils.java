package xb.dev.document.temp;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xb.dev.document.handler.impl.DefaultExcelHandle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * excel parse tool
 * create by huangxb on 2018/6/21
 *
 */
public class ExcelUtils {

    private final static Logger logger=LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 表格解析
     * @param target 与表格记录定义的实体类
     * @param path 本地文件路径
     * @param logger 日志记录
     * @param <T>
     * @return 返回map对象，key为excel中工作表名称，value为该工作表解析出的记录列表
     */
    public static <T>  Map<String,List<T>> excelParse(Class<T> target,String path,Logger logger) throws ExcelParseException{

        Map<String,List<T>> results=new ExcelHandler<T>(target,path,logger).parse();
        return results;
    }
    /**
     * 表格解析
     * @param target 与表格记录定义的实体类
     * @param is excel字节流对象
     * @param logger 日志记录
     * @param <T>
     * @return 返回map对象，key为excel中工作表名称，value为该工作表解析出的记录列表
     */
    public static <T>  Map<String,List<T>> excelParse(Class<T> target, InputStream is,Logger logger) throws ExcelParseException{
        Map<String,List<T>> results=new ExcelHandler<T>(target,is,logger).parse();
        return results;
    }

    public static void main(String[] args) throws Exception {
        //添加类目
        category();
        //添加参数
//        prop();
        //添加商品参数
//        goodsProp();
    }


    public static void goodsProp() throws Exception{
        Map<String,List<GoodsPropData>> map = excelParse(GoodsPropData.class,"D:/goodsProp20180903.xlsx",logger);

        List<GoodsPropInsert> inserts = new ArrayList<>();

        List<GoodsPropData> result = map.get("Sheet1");
        //一级类目
        String one = "";
        //二级类目
        String two = "";
        //三级类目数组
        String[] threes = null;
        boolean start = false;
        List<GoodsProp> goodsPropList = new ArrayList<>();
        for(GoodsPropData g:result){
            if(g.getPropEn()!=null&&!"".equals(g.getPropEn().trim())){
                //等于product name
                if(g.getPropEn().replaceAll("\\s","").equalsIgnoreCase("Product name".replaceAll("\\s",""))){
                    if(start){//start = true 表示遇到的是第二个产品名称
                        //表示到达下一属性集合，此时应该进行保存操作再继续向下
                        List<GoodsProp> gpl = new ArrayList<>(goodsPropList);
                        for(String three:threes){
                            GoodsPropInsert goodsPropInsert = new GoodsPropInsert();
                            goodsPropInsert.setOne(one);
                            goodsPropInsert.setTwo(two);
                            goodsPropInsert.setThree(three);
                            goodsPropInsert.setPropList(gpl);
                            inserts.add(goodsPropInsert);
                            goodsPropList.clear();
                            GoodsProp goodsProp = new GoodsProp();
                            goodsProp.setMustFill(g.getMustFill());
                            goodsProp.setPropEn(g.getPropEn());
                            goodsProp.setPropType(g.getPropType());
                            goodsProp.setPropValueEn(g.getPropValueEn());
                            goodsProp.setSpecsEn(g.getSpecsEn());
                            goodsProp.setSpecsValueEn(g.getSpecsValueEn());
                            goodsPropList.add(goodsProp);
                        }

                    }else {//start = false 表示遇到的是第一个个产品名称
                        GoodsProp goodsProp = new GoodsProp();
                        goodsProp.setMustFill(g.getMustFill());
                        goodsProp.setPropEn(g.getPropEn());
                        goodsProp.setPropType(g.getPropType());
                        goodsProp.setPropValueEn(g.getPropValueEn());
                        goodsProp.setSpecsEn(g.getSpecsEn());
                        goodsProp.setSpecsValueEn(g.getSpecsValueEn());
                        goodsPropList.add(goodsProp);
                        start = true;
                    }
                }else{
                    //不等于product name表示是参数
                    GoodsProp goodsProp = new GoodsProp();
                    goodsProp.setMustFill(g.getMustFill());
                    goodsProp.setPropEn(g.getPropEn());
                    goodsProp.setPropType(g.getPropType());
                    goodsProp.setPropValueEn(g.getPropValueEn());
                    goodsProp.setSpecsEn(g.getSpecsEn());
                    goodsProp.setSpecsValueEn(g.getSpecsValueEn());
                    goodsPropList.add(goodsProp);
                }
            }
            //判断一级类目
            if(g.getOne()!=null&&!"".equals(g.getOne().trim())){
                one = g.getOne();
            }
            //判断二级类目
            if(g.getTwo()!=null&&!"".equals(g.getTwo().trim())){
                two = g.getTwo();
            }
            //判断三级类目
            if(g.getThree()!=null&&!"".equals(g.getThree().trim())){
                threes = g.getThree().split(",");
            }
        }
        System.out.println();
    }

    public static void prop() throws Exception{
        Map<String,List<PropData>> map = excelParse(PropData.class,"D:/prop.xlsx",logger);
        List<PropData> result = map.get("大宗类目透视表");
        String prop = "";
        String one = "";
        String two = "";
        String  three= "";
        String error = "(空白),#N/A";
        for(PropData c:result){
            if (c.getProp()!=null&&!"".equals(c.getProp().trim())&&!"(空白)".equals(c.getProp().trim())) {
                prop=c.getProp();
            }
            if (c.getOne()!=null&&(!"".equals(c.getOne().trim()))) {
                one = c.getOne();
            }
            if (c.getTwo()!=null&&(!"".equals(c.getTwo().trim()))) {
                two = c.getTwo();
            }
            if (c.getThree()!=null&&(!"".equals(c.getThree().trim()))) {
                three = c.getThree();
            }
            if(prop.indexOf(error)>-1||one.indexOf(error)>-1||two.indexOf(error)>-1||three.indexOf(error)>-1){
                        //
            }else {
                insertProp(prop, one, two, three);
            }
        }
    }

    public static void category() throws Exception{
        Map<String,List<CategoryData>> map = excelParse(CategoryData.class,"D:/category_1.xlsx",logger);

        int zero = 0;
        int one = 0;
        int two = 0;
        int  three= 0;
        List<CategoryData> result = map.get("零级+一级+二级+三级");
        for(CategoryData c:result){
            if (c.getZero()!=null&&(!"".equals(c.getZero().trim()))) {
                zero = getId(c.getZero(),0);
            }
            if (c.getOne()!=null&&(!"".equals(c.getOne().trim()))) {
                one = getId(c.getOne(),zero);
            }
            if (c.getTwo()!=null&&(!"".equals(c.getTwo().trim()))) {
                two = getId(c.getTwo(),one);
            }
            if (c.getThree()!=null&&(!"".equals(c.getThree().trim()))) {
                three = getId(c.getThree(),two);
            }
        }
    }

    public static void insertProp(String propName, String categoryName1, String categoryName2, String categoryName3){
        try {
            propName = URLEncoder.encode(propName,"utf-8");
            categoryName1 = URLEncoder.encode(categoryName1,"utf-8");
            categoryName2 = URLEncoder.encode(categoryName2,"utf-8");
            categoryName3 = URLEncoder.encode(categoryName3,"utf-8");
            String url = "http://192.168.10.158:8081/propsManage/batchAddCaItemProp?propName=" + propName + "&categoryName1=" + categoryName1+ "&categoryName2=" + categoryName2+ "&categoryName3=" + categoryName3;
            URL urlConnect = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnect.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            //设置是否从HttpURLConnection读入内容，默认为true
            connection.setDoInput(true);
            connection.setRequestProperty("Charset", "utf-8");
            connection.setRequestProperty("Connection","keep-alive");
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            String s = bufferedReader.readLine();
            if(!"true".equals(s)){
                System.out.println("insert error : propName : "+propName+ ",categoryName1 : "+ categoryName1 +  ",categoryName2 : " + categoryName2 + ",categoryName3 : " + categoryName3);
            }else{
                System.out.println("insert success : propName : "+propName+ ",categoryName1 : "+ categoryName1 +  ",categoryName2 : " + categoryName2 + ",categoryName3 : " + categoryName3);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("数据异常");
        }
    }

    public static Integer getId(String name,Integer parentId){
        try {
            name = URLEncoder.encode(name.trim(),"utf-8");
            String url = "http://192.168.10.158:8081/category/addCaItemCategoryWithBack?categoryName=" + name + "&parentId=" + parentId;
            URL urlConnect = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnect.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            //设置是否从HttpURLConnection读入内容，默认为true
            connection.setDoInput(true);
            connection.setRequestProperty("Charset", "utf-8");
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            String s = bufferedReader.readLine();
            System.err.println("insert "+name+" success");
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("insert "+name+" error");
            throw new RuntimeException("数据异常");
        }
    }
}

