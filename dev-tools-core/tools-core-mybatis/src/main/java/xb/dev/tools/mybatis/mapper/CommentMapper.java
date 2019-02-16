package xb.dev.tools.mybatis.mapper;

import xb.dev.tools.mybatis.dao.entity.Comment;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-11 11:13:26
 */
public interface CommentMapper {
    /**
     * 返回数据表当前最大id
     * @return
     */
    int queryMaxId();
    /**
     * 新增
     * @param comment 评论信息
     */
    void insert(Comment comment);

    /**
     * 根据新闻id查询新闻下的评论列表
     * @param newsId 新闻id
     * @return
     */
    Comment queryByNewsId(int newsId);
}
