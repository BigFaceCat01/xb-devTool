package xb.dev.document;


import com.alibaba.fastjson.JSON;
import xb.dev.document.config.DocumentHandlerBuilder;
import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.document.handler.DocumentHandler;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by huangxb on 2018-07-27 09:44:51
 * @Description: 文档处理工具类
 */
public class DocumentUtil {
    /**
     * 通过传入本地文件路径进行文档处理
     * @param target 返回的目标类类型
     * @param path 文件绝对路径
     * @param <T>
     * @return
     * @throws DocumentHandlerException
     */
    public static <T> Map<String,List<T>> getDataByPath(Class<T> target, String path) throws DocumentHandlerException {
        //获得对应的文档处理对象
        DocumentHandler documentHandler =new DocumentHandlerBuilder().buildConfig(target).build(path,null);

        return documentHandler.parse();
    }

    /**
     * 通过传入流解析文档对象
     * @param target 返回的目标类类型
     * @param is 流对象
     * @param <T>
     * @return
     * @throws DocumentHandlerException
     */
    public static <T> Map<String,List<T>> getDataByStream(Class<T> target, InputStream is) throws DocumentHandlerException {
        //获得对应的文档处理对象
        DocumentHandler documentHandler =new DocumentHandlerBuilder().buildConfig(target).build(null,is);

        return documentHandler.parse();
    }

    /**
     * 返回文档输出流对象，如果是excel文档，默认返回xls格式
     * @param data 用于构建文档的数据
     * @param target 数据列表对应的实体类
     * @param <T>
     * @return
     * @throws DocumentHandlerException
     */
    public static <T> InputStream createDocument(List data,Class<T> target) throws DocumentHandlerException {
        //获得对应的文档处理对象
        DocumentHandler documentHandler =new DocumentHandlerBuilder().buildConfig(target).build(data);

        return documentHandler.outputDocument();
    }

    public static void main(String[] args) throws Exception {




        System.out.println();
        //List<BankStatementEntity> data = map.get("DOCUMENT");

        //InputStream is = createDocument(data,BankStatementEntity.class);

//        FileOutputStream fos = new FileOutputStream("D:/27.xls");
//        int len = 0;
//        byte[] b = new byte[1024];
//        while((len = is.read(b))!=-1){
//            fos.write(b,0,len);
//        }
//        System.out.println();
//        is.close();
//        fos.close();
    }

}
