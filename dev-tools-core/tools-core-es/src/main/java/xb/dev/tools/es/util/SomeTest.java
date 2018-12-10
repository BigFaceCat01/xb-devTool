package xb.dev.tools.es.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xb.dev.document.DocumentUtil;
import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.tools.es.model.HSCodeModel;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: Created by huangxb on 2018-08-03 14:51:28
 * @Description:
 */
public class SomeTest {
    public static final String baseUrl = "http://www.jj20.com";
    private static final Random r = new Random();

    public static void main(String[] args){
    }


    public static void testHSCode(){
        try {
            Map<String,List<HSCodeModel>> result =  DocumentUtil.getDataByPath(HSCodeModel.class,"D:/HSCode.xls");
            System.out.println();
        } catch (DocumentHandlerException e) {
            e.printStackTrace();
        }
    }
    public static void download(String path){
        String result = HttpUtil.getTextContentFromUrl("http://www.jj20.com/bz/zrfg","gbk",null);
        Document document = Jsoup.parse(result);
        Elements pages = document.getElementsByClass("page");
        //页码,用于翻页
        Elements page = pages.get(0).getElementsByTag("option");
        //获得图片列表
        Elements imgUL = document.getElementsByClass("pic2 vvi fix");
        Elements imgList = imgUL.select("a[target]");
        //采集图片路径
        String imgDetail = HttpUtil.getTextContentFromUrl(baseUrl+imgList.get(0).attr("href"),"gbk",null);
        Document imgDetailDoc = Jsoup.parse(imgDetail);
        Element bigImgEle = imgDetailDoc.getElementById("showImg");
        Elements imgGroup = bigImgEle.getElementsByTag("img");
        //图片基本路径,请求参数包括p = 页面图片展示路径 ，w = 宽 ， h = 高 ，单位像素
        String imgBaseUrl = "http://cj.jj20.com/d/cj0.php";
        //组装图片路径，放入图片下载队列中
        for(Element e:imgGroup){
            String u = e.attr("src").substring(baseUrl.length()+1);
            System.out.println(u);
            String uu = u.replace("-lpp","");
            String param = "p="+uu+"&w=1920&h=1080";
            String name = uu.substring(uu.lastIndexOf("/")+1);
            HttpUtil.downResource(imgBaseUrl+"?"+param,path+name);
        }
    }

}
