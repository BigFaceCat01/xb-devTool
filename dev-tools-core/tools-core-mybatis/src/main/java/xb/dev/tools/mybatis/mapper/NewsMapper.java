package xb.dev.tools.mybatis.mapper;

import xb.dev.tools.mybatis.dao.entity.NewsEntity;

import java.util.List;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-11 11:28:04
 */
public interface NewsMapper {
    /**
     * 返回数据表当前最大id
     * @return
     */
    int queryMaxId();
    /**
     * 查询全部新闻，没有任何过滤条件
     * @return 新闻列表
     */
    List<NewsEntity> queryAll();

    /**
     * 根据id查询新闻
     * @param id 新闻id
     * @return 新闻
     */
    NewsEntity queryById(int id);

    /**
     * 通过新闻id删除新闻
     * @param id 新闻id
     * @return
     */
    int deleteById(int id);

    /**
     * 添加新闻，不过滤为空的字段
     * @param newsEntity 新闻实体
     * @return
     */
    int insertNews(NewsEntity newsEntity);

    /**
     * 查询新闻总数，没有任何过滤条件
     * @return
     */
    long countAll();

    /**
     * 修改新闻，为空的字段不参与修改
     * @param newsEntity 新闻实体
     * @return
     */
    int updateCommonById(NewsEntity newsEntity);

    /**
     * 逻辑删除新闻
     * @param newsId 新闻id
     * @return
     */
    int deleteNewsById(int newsId);
}
