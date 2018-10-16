package xb.dev.tools.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Created by huangxb
 * @date 2018-10-16 17:14:02
 */
public class HttpUtil {
    /**
     * 获得指定url路径的文本响应结果
     * @param url
     * @return
     */
    public static String getContentFromUrl(String url){
        if(url==null){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(1024);
        URLConnection urlConnection;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            URL u = new URL(url);
            urlConnection = u.openConnection();
            inputStreamReader = new InputStreamReader(urlConnection.getInputStream(),"gbk");
            bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            while((s=bufferedReader.readLine())!=null){
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStreamReader.close();
                bufferedReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    return null;
    }
}
