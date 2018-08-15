package xb.dev.document.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
  * @Author:cuijialei
  * @Description 基本工具类 from ca-b2b -commons
  * @Date:2018/6/13_15:33
  */
public class StringTool {

    private static final Logger logger = LoggerFactory.getLogger(StringTool.class);
    private static boolean isThreadLocalRandomAvailable = false;
    private static long leastSigBits;
    private static ReentrantLock lock = new ReentrantLock();
    private static Random random;
    private static long lastTime;
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    static {
        try {
            isThreadLocalRandomAvailable = null != com.zhongfei.data.document.common.StringTool.class.getClassLoader().loadClass("java.util.concurrent.ThreadLocalRandom");
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException",e);
        }
        byte[] seed = new SecureRandom().generateSeed(8);
        leastSigBits = new BigInteger(seed).longValue();
        if (!isThreadLocalRandomAvailable)
            random = new SecureRandom(seed);
    }

    private StringTool() {
        /**
         * 构造函数，禁止实例化
         */
    }

    /**
     * 空对象判断
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        if (obj == null)
            return true;
        else if ("".equals(obj))
            return true;
        else if ("null".equals(obj))
            return true;
        return false;
    }

    /**
     * 空对象判断
     *
     * @param obj
     * @return
     */
    public static boolean isNotBlank(Object obj) {
        if (obj == null)
            return false;
        else if ("null".equals(obj))
            return false;
        else if ("".equals(obj))
            return false;
        return true;
    }

    /**
     * 处理空字符串
     *
     * @param str 字符串对象
     * @return
     */
    public static String replaceNull(String str) {
        if (str == null)
            return "";
        else if ("null".equals(str))
            return "";
        else
            return str;
    }

    /**
     * 字节转换为浮点
     *
     * @param b 字节（至少4个字节）
     * @param index 开始位置
     * @return
     */
    public static float byte2Float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    /**
     * 字符数组转Double
     *
     * @param arr
     * @return
     */
    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++)
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        return Double.longBitsToDouble(value);
    }

    /**
     * long转byte[]
     *
     * @param num
     * @return
     */
    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    /**
     * char转byte
     *
     * @param c char
     * @return byte
     */
    public static byte char2Byte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexStr(byte[] b) {
        StringBuilder hexStr = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hexStr.append("0" + stmp);
            else
                hexStr.append(stmp);
        }
        return hexStr.toString().toLowerCase(Locale.getDefault());
    }

    /**
     * String的字符串转换成unicode的String
     */
    public static String str2Unicode(String strText) {
        char c;
        StringBuilder strRet = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
                strRet.append("//u" + strHex);
            else
                strRet.append("//u00" + strHex);	// 低位在前面补00
        }
        return strRet.toString();
    }

    /**
     * unicode的String转换成String的字符串
     */
    public static String unicode2Str(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    /**
     * 生成指定长度的随机数字串
     *
     * @param length
     * @return
     */
    public static String random(int length) {
        int max = 9;
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int s = random.nextInt(max);
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 生成新文件名
     *
     * @param fileName
     * @return
     */
    public static String getNewFileName(String fileName) {
        if (!isBlank(fileName)) {
            int dot = fileName.lastIndexOf('.');
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            if ((dot > -1) && (dot < (fileName.length() - 1)))
                return sdf.format(new Date()) + random(3) + fileName.substring(dot);
        }
        return fileName;
    }


    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }


    /**
     * 获取文件后缀
     *
     * @param fileName 文件名
     * @return
     */
    public static String getSuffix(String fileName) {
        if (isBlank(fileName) || fileName.indexOf('.') < 0)
            return null;
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    /**
     * 获取图片类型
     *
     * @param fileExt 文件后缀
     * @return
     */
    public static byte getImgType(String fileExt) {
//        if (fileExt == null)
//            throw new ServiceException("文件后缀错误");
        if (fileExt == null)
            return -1;  //文件后缀错误
        fileExt = fileExt.toUpperCase();
        if ("PNG".equals(fileExt))
            return 1;
        else if ("JPG".equals(fileExt))
            return 2;
        else if ("GIF".equals(fileExt))
            return 3;
        else
            return 0;
    }
}
