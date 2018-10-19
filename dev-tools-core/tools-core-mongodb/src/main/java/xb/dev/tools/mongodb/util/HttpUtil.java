package xb.dev.tools.mongodb.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author Created by huangxb
 * @date 2018-10-16 17:14:02
 */
@Slf4j
public class HttpUtil {

    public static final int OK_STATUS_CODE = 200;


    /**
     * 返回文本格式的请求响应体,请求后即会关闭连接,不适用短时间进行大量请求
     * @param url 网络路径
     * @param charset 字符集
     * @param requestHeaders 请求头
     * @return 文本响应
     */
    public static String getTextContentFromUrl(String url, String charset, Map<String,String> requestHeaders){
        //判空
        if(url==null){
            return null;
        }
        //创建
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http get请求对象
        HttpGet httpGet = new HttpGet(url);
        //添加请求头
        if(requestHeaders != null){
            requestHeaders.forEach((key,value)->
                httpGet.addHeader(key,value)
            );
        }
        log.info("请求路径:{}",url);
        try {
            //执行请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //请求是否成功
            if(response.getStatusLine().getStatusCode()==OK_STATUS_CODE){
                //获得响应实体对象
                HttpEntity responseEntity = response.getEntity();
                if(responseEntity != null) {
                    return EntityUtils.toString(responseEntity,charset);
                }
            }else {
                log.warn("{}响应状态码为{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("路径[{}]请求失败",url);
            e.printStackTrace();
        }
        finally {
            //关闭连接
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void downResource(String url,String path){
        //判空
        if(url==null){
            return;
        }
        //创建
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http get请求对象
        HttpGet httpGet = new HttpGet(url);
        log.info("请求路径:{}",url);

        try {
            //执行请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //请求是否成功
            if(response.getStatusLine().getStatusCode()==OK_STATUS_CODE){
                //获得响应实体对象
                byte[] b = new byte[1024];
                InputStream is = response.getEntity().getContent();
                OutputStream os = new FileOutputStream(path);
                int len = 0;
                while((len = is.read(b))!=-1){
                    os.write(b,0,len);
                }
                os.flush();
                os.close();
                is.close();
            }else {
                log.warn("{}响应状态码为{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("路径[{}]请求失败",url);
            e.printStackTrace();
        }
        finally {
            //关闭连接
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
