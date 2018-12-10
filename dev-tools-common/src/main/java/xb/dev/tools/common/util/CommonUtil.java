package xb.dev.tools.common.util;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-10 17:42:41
 */
public class CommonUtil {

    private CommonUtil(){}
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
        return result.toString();
    }
}
