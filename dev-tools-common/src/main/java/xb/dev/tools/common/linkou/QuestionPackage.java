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
        question_one("pwwkew");
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
    public static void question_two(int[] num1,int[] num2){
        //获得两个数组的长度
        int len = num1.length;
        int len2 = num2.length;
        if(num1[len -1] <= num2[0]){
            //如果num1的最大值小于等于num2的最小值

        }else if(num1[0] >= num2[len2 -1]){
            //如果num1的最小值大于等于num2的最大值

        }
        //获得num1,num2较大的一组
        int[] max = num1[0] >= num2[0] ? num1 : num2;
        int[] min = num1[0] >= num2[0] ? num2 : num1;
        //获得中位数索引
        int[] index ;
        int indexCount = 0;
        if(len + len2 % 2 == 0){
            //总长度为偶数，中位数用中间两个数求得
            index = new int[]{((len + len2) /2 -1),(len + len2) / 2};
            indexCount = 2;
        }else {
            //总长度为奇数，中位数等于中间那个数
            index = new int[]{(len + len2) /2};
            indexCount = 1;
        }
        int left = 0, right = min.length , middle;
        while (true){
            middle = (left + right) / 2;
            if(min[middle] > max[0]){
                right = middle;
            }else {
                left = middle;
            }
            if(right - left == 1){
                break;
            }
        }
    }
}
