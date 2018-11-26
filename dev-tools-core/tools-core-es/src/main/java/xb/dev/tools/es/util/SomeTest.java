package xb.dev.tools.es.util;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xb.dev.document.DocumentUtil;
import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.tools.es.model.HSCodeModel;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Created by huangxb on 2018-08-03 14:51:28
 * @Description:
 */
public class SomeTest {
    public static final String baseUrl = "http://www.jj20.com";
    private static final Random r = new Random();

    public static void main(String[] args){
        System.out.println(bigNumPlus(new StringBuilder().append("619"),new StringBuilder().append("0")));
    }

    /**
     * 大位数相加
     * @param num 大位数
     * @param num2 大位数
     * @return 结果
     */
    public static String bigNumPlus(StringBuilder num,StringBuilder num2){
        StringBuilder result = new StringBuilder(255);
        //进位标志
        int carryBit = 0;
        //以下用于包装snum长度始终大于snum2，len 始终大于len2
        int len = num.length();
        int len2 = num2.length();
        int temp = 0;
        StringBuilder snum;
        StringBuilder snum2;
        if(len>=len2){
            snum = num;
            snum2 = num2;
        }else {
            snum = num2;
            snum2 = num;
            temp = len;
            len = len2;
            len2 = temp;
        }
        int lenTemp = len -1;
        int len2Temp = len2 -1;
        while(true){
            //获得最后一位，进行求和
            String number = snum.charAt(lenTemp)+"";
            String number2 = snum2.charAt(len2Temp) + "";
            //两数之和
            int sum = Integer.valueOf(number) + Integer.valueOf(number2) + carryBit;
            //获得进位值
            carryBit = sum / 10;
            //两数之和余数
            result.insert(0,sum%10);
            //继续向前求两数之和
            lenTemp--;
            len2Temp--;
            //如果长度较短的数已相加完
            if(len2Temp < 0){
                //获得大数剩余未加的位数
                int rest = len - len2;
                //如果两数长度一致，且有进位，则将进位直接赋予数首位
                if(rest == 0 && carryBit != 0){
                    result.insert(0,carryBit);
                    break;
                }
                //如果两数长度一致，且无进位，则结果已计算完成
                if(rest == 0 && carryBit == 0){
                    break;
                }
                //进位与大数未加得数相加
                for (int j = rest -1; j >=0 ; j--) {

                    String n = snum.charAt(j)+"";
                    int su = Integer.valueOf(n) + carryBit;
                    //进位值
                    carryBit = su / 10;
                    result.insert(0,su%10);
                }
                if(carryBit != 0){
                    result.insert(0,carryBit);
                }
                break;
            }
        }
        //将结果逆向生成链表
        int size = result.length();
        ListNode listNode = null;
        ListNode tempNode = null;
        for (int i = size-1; i >=0 ; i--) {
            if(listNode == null){
                listNode = new ListNode(Integer.valueOf(result.charAt(i)+ ""));
                tempNode = listNode;
            }else {
                tempNode.next = new ListNode(Integer.valueOf(result.charAt(i) + ""));
                tempNode = tempNode.next;
            }
        }
        return result.toString();
    }

    public static void twoPlus(ListNode l1,ListNode l2){
        //用于存储链表数据
        StringBuilder s = new StringBuilder(10);
        StringBuilder s2 = new StringBuilder(10);
        //获得l1链表
        do{
            s.insert(0,l1.val);
            l1 = l1.next;
        }while (l1 != null);
        //获得l2链表
        do{
            s2.insert(0,l2.val);
            l2 = l2.next;
        }while (l2 != null);
        //获得链表的整数值
        long n0 = Long.valueOf(s.toString());
        long n2 = Long.valueOf(s2.toString());
        //获得结果
        long n3 = n0+n2;
        //清除，用来存储结果
        s.delete(0,s.length());
        s.append(n3);
        //将结果逆向生成链表
        int size = s.length();
        ListNode listNode = null;
        ListNode temp = null;
        for (int i = size-1; i >=0 ; i--) {
            if(listNode == null){
                listNode = new ListNode(Integer.valueOf(s.charAt(i)+ ""));
                temp = listNode;
            }else {
                temp.next = new ListNode(Integer.valueOf(s.charAt(i) + ""));
                temp = temp.next;
            }
        }
        System.out.println();
    }

    public static void twoSum(){
        int[] num = {3,2,4};

        int target = 6;
        //传入数组的长度
        int size = num.length;
        //复制数组用于排序
        int[] sortNums = new int[size];
        System.arraycopy(num,0,sortNums,0,size);
        //排序
        Arrays.sort(sortNums);
        //获得数组中与target相等或小于的最大索引
        int max = -1;
        for (int i = 0; i < size; i++) {
            if(sortNums[i] > target){
                max = i-1;
            }
        }
        //若所有值比target小，则赋值最后一个值得索引为max
        if(max == -1){
            max = size-1;
        }

        int min = 0;
        while(true){
            //如果最小值加最大值大于目标值则，最大索引减一
            if(sortNums[min] + sortNums[max] > target){
                max--;
            }
            //如果最小值加最大值大于目标值则，最小索引加一
            if(sortNums[min] + sortNums[max] < target){
                min++;
            }
            //获得结果
            if(sortNums[min] + sortNums[max] == target){
                break;
            }
        }
        //获得结果在原来数组中的索引
        int le = -1,re = -1;
        for (int i = 0; i < size; i++) {
            if(num[i] == sortNums[min] && le==-1){
                le = i;
                continue;
            }
            if(num[i] == sortNums[max] && re==-1){
                re = i;
            }
        }
        System.out.println(le+"-"+re);
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
@Data
class Stone{
    private Integer id;
    private String name;

    public Stone(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
