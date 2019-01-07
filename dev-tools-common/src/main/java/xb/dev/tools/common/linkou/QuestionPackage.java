package xb.dev.tools.common.linkou;

import java.util.HashSet;
import java.util.Set;

/**
 * @author create by huang xiao bao
 * @date 2018-12-8 21:52
 * 领扣题解
 */
public final class QuestionPackage {

    private QuestionPackage(){}

    public static void main(String[] args) {
        //题目 1
//        question_one("pwwkew");
        //题目2
//        System.out.println(question_two(new int[]{},new int[]{10,12}));
        //题目3
        System.out.println(question_three("abb"));
    }

    /**
     * 题目 1
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public static void question_one(String s){
        //最长子串
        String maxLengthSubString = "";
        //最长子串长度
        int maxLength = 0;

        int len = s.length();
        //子串左，右索引
        int left,right = 0,temp;
        //滑动窗口
        Set<Character> slideWindow = new HashSet<>(512);
        for(int i=0;i< len; i++){
            //如果剩余未验证子串长度比当前最长子串长度小，则直接退出
            if(len - i < maxLength){
                break;
            }
            //right使用上一次判断结果
            left = i;
            while (true){
                //如果right == 字符总长度
                if(right == len){
                    temp = slideWindow.size();
                    if(temp > maxLength){
                        maxLength = temp;
                        maxLengthSubString = s.substring(left,right);
                        System.out.println(maxLengthSubString);
                    }
//                    slideWindow.clear();
                    break;
                }

                char c = s.charAt(right);
                //判断当前滑动窗口是否包含该字符
                if(!slideWindow.contains(c)) {
                    slideWindow.add(c);
                    right++;
                }else {
                    temp = slideWindow.size();
                    if(temp > maxLength){
                        maxLength = temp;
                        maxLengthSubString = s.substring(left,right);
                        System.out.println(maxLengthSubString);
                    }
                    //清楚滑动窗口字符
                    slideWindow.remove(s.charAt(left));
                    break;
                }
            }
        }
        System.out.println("最长子串："+ maxLengthSubString);
        System.out.println("最长子串长度："+ maxLength);
    }

    /**
     * 题目 2
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     * @param num1
     * @param num2
     */
    public static double question_two(int[] num1,int[] num2){
        //获得两个数组的长度
        int len = num1.length;
        int len2 = num2.length;
        boolean numLeft = false;
        boolean num2Left = false;
        //中位数索引
        int middle = (len + len2) / 2;
        //总长度是否是偶数
        boolean odd = (len + len2) % 2 == 0;
        //判断两个数组的有序性状态，从小到大，或者从大到小
        if(len == 0 || num1[0] <= num1[len -1]){
            //从小到大
            numLeft = true;
        }
        if(len2 == 0 || num2[0]<=num2[len2 - 1]){
            //从小到大
            num2Left = true;
        }
        //存储两个数组最终排序结果
        int[] temp = new int[len+len2];
        if(numLeft){
            //数组1从小到大
            if(num2Left){
                //数组2从小到大
                return fromLeftAndFromLeft(temp,num1,num2,len,len2,odd,middle);
            }else {
                //数组2从大到小
                return fromLeftAndFromRight(temp,num1,num2,len,len2,odd,middle);
            }
        }else {
            //数组1从大到小
            if(num2Left){
                //数组2从小到大
                return fromRightAndFromLeft(temp,num1,num2,len,len2,odd,middle);
            }else {
                //数组2从大到小
                return fromRightAndFromRight(temp,num1,num2,len,len2,odd,middle);
            }
        }
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * @param s 源字符串
     * @return 回文子串
     */
    public static String question_three(String s){
        int currentIndex=0;
        int len = s.length();
        if(len == 1 || len ==0){
            return s;
        }
        String subString = String.valueOf(s.charAt(0));
        for(int i=0;i<len;i++){
            if(currentIndex != i){
                String ss = getSubString(currentIndex,i,s,len);
                if(ss.length() > subString.length()){
                    subString = ss;
                }
                currentIndex = i;
            }else {
                currentIndex = i;
            }
        }
        return subString;
    }
    private static String getSubString(int start,int end,String source,int len){
        if(source.charAt(start) == source.charAt(end)){
            if(end != len && start == 0 ){
                return source.substring(start,end+1);
            }else if(end == (len-1) || start == 0 ){
                return source.substring(start,len);
            }
            return getSubString(--start,++end,source,len);
        }else {
            if(end - start ==1) {
                return "";
            }else {
                return source.substring(start+1,len-1);
            }
        }
    }

    private static double fromRightAndFromRight(int[] temp, int[] num1, int[] num2, int len, int len2, boolean odd, int middle) {
        int tem ;
        for(int i = 0;i<len/2;i++){
            tem = num1[len-i-1];
            num1[len-i-1] = num1[i];
            num1[i] = tem;
        }
        for(int i = 0;i<len2/2;i++){
            tem = num2[len2-i-1];
            num2[len2-i-1] = num2[i];
            num2[i] = tem;
        }
        return fromLeftAndFromLeft(temp,num1,num2,len,len2,odd,middle);
    }

    private static double fromRightAndFromLeft(int[] temp, int[] num1, int[] num2, int len, int len2, boolean odd, int middle) {
        int tem ;
        for(int i = 0;i<len/2;i++){
            tem = num1[len-i-1];
            num1[len-i-1] = num1[i];
            num1[i] = tem;
        }
        return fromLeftAndFromLeft(temp,num1,num2,len,len2,odd,middle);
    }

    private static double fromLeftAndFromLeft(int[] temp,int[] num1,int[] num2,int len,int len2,boolean odd,int middle){
        int n1 = 0;
        int i = 0;
        int n2 = 0;
        while(true) {
            //判断是否某一数组已经循环结束
            if(n1 == len){
                //数组1循环结束,只需要循环数组2
                for(;n2<len2;n2++){
                    if(i == middle ){
                        if(odd){
                            //如果是偶数，中位数为middle,middle+1，两个索引位置的值得平均值
                            System.out.println("数组："+temp);
                            System.out.println("偶数时："+num2[n2]);
                            return (temp[i-1]+num2[n2]) / 2.0;
                        }else {
                            //如果两个数组长度和是奇数
                            System.out.println("数组："+temp.toString());
                            return num2[n2];
                        }
                    }
                    temp[i] = num2[n2];
                    i++;
                }
            }else if(n2 == len2){
                //数组2循环结束,只需要循环数组1
                for(;n1<len;n1++){
                    if(i == middle ){
                        if(odd){
                            //如果是偶数，中位数为middle,middle+1，两个索引位置的值得平均值
                            System.out.println("数组："+temp.toString());
                            System.out.println("偶数时："+num1[n1]);
                            return (temp[i-1]+num1[n1])/2.0;
                        }else {
                            //如果两个数组长度和是奇数
                            System.out.println("数组："+temp.toString());
                            return num1[n1];
                        }
                    }
                    temp[i] = num1[n1];
                    i++;
                }
            }
            if(len == 0){
                temp[i] = num2[n2];
                n2++;
            }else if(len2 == 0){
                temp[i] = num1[n1];
                n1++;
            }else if (num1[n1] > num2[n2]) {
                temp[i] = num2[n2];
                n2++;
            }else {
                temp[i] = num1[n1];
                n1++;
            }
            //如果已到中位数的索引位置
            if(i == middle ){
                if(odd){
                    //如果是偶数，中位数为middle,middle+1，两个索引位置的值得平均值
                    System.out.println("数组："+temp.toString());
                    System.out.println("偶数时："+temp[i]);
                    return (temp[i-1]+temp[i])/2.0;
                }else {
                    //如果两个数组长度和是奇数
                    System.out.println("数组："+temp.toString());
                    return temp[i];
                }
            }
            i++;
        }
    }
    private static double fromLeftAndFromRight(int[] temp,int[] num1,int[] num2,int len,int len2,boolean odd,int middle){
        int tem ;
        for(int i = 0;i<len2/2;i++){
            tem = num2[len2-i-1];
            num2[len2-i-1] = num2[i];
            num2[i] = tem;
        }
        return fromLeftAndFromLeft(temp,num1,num2,len,len2,odd,middle);
    }
}
