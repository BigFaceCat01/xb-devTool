package xb.dev.tools.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Created by huangxb on 2018-08-01 18:02:33
 * @Description: 新闻评论实体类
 */
@Getter
@Setter
public class Comment {
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date commentTime;
    /**
     * 评论状态
     */
    private Byte status;
}
