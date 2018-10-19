package xb.dev.tools.mongodb.constant;

/**
 * @author Created by huangxb
 * @date 2018-09-25 17:30:39
 */
public class NewsContants {
    /**
     * 用于初始化新闻浏览数量等
     */
    public static final long NEWS_ZERO = 0L;
    /**
     * 新闻未删除
     */
    public static final boolean NEWS_NOT_DELETE = false;
    /**
     * 新闻已删除
     */
    public static final boolean NEWS_DELETE = true;
    /**
     * 新闻待提交
     */
    public static final byte WAIT_CONFIRM = 0;
    /**
     * 新闻审核中
     */
    public static final byte CHECKING = 1;

}
