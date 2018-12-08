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
}
