package xb.dev.tools.es.service;


import xb.dev.tools.es.dao.entity.EsNewsEntity;

import java.util.List;
import java.util.Set;

/**
 * @author Created by huangxb on 2018-08-03 18:03
 *
 */
public interface EsNewsService {
    /**
     * 搜索建议
     * @param keywords 搜索的关键词
     * @return 关键词相关的词条列表
     */
    Set<String> suggest(String keywords);

    /**
     * 新增一个新闻到搜索引擎
     * @param esNewsEntity 新闻信息
     */
    void insert(EsNewsEntity esNewsEntity);

    /**
     * 根据id查询新闻
     * @param id 新闻id
     * @return 新闻信息
     */
    EsNewsEntity getById(String id);


    /**
     * 精确匹配，使用条件或
     * @param title 新闻标题
     * @param source 来源
     * @param type 分类标签
     * @return 新闻列表
     */
    List<EsNewsEntity> listBy(String title, String source, String type);


    /**
     * 同步网易新闻到es
     */
    void syncNews163com();
}
