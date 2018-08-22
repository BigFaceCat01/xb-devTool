package xb.dev.document.temp;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<String,List<CategoryData>> map = excelParse(CategoryData.class,"D:/category.xlsx",logger);
        int zero = 0;
        int one = 0;
        int two = 0;
        int  three= 0;
        Map<String,Map<String,Map<String,String>>> api = new HashMap<>();
        List<CategoryData> result = map.get("零级+一级+二级+三级");
        for(CategoryData c:result){
            if (c.getZero()!=null&&(!"".equals(c.getZero()))) {
                zero = getId(c.getZero(),0);
            }
            if (c.getZero()!=null&&(!"".equals(c.getOne()))) {
                one = getId(c.getOne(),zero);
            }
            if (c.getZero()!=null&&(!"".equals(c.getTwo()))) {
                two = getId(c.getTwo(),one);
            }
            if (c.getZero()!=null&&(!"".equals(c.getThree()))) {
                three = getId(c.getThree(),two);
            }
        }
    }

    public static Integer getId(String name,Integer parentId){
        try {
            name = URLEncoder.encode(name,"utf-8");
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
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("数据异常");
        }
    }
}

