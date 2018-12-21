package xb.dev.tools.jpa.service;

import xb.dev.tools.jpa.model.JpaNewsInfoModel;
import xb.dev.tools.jpa.model.NewsSaveModel;
import xb.dev.tools.jpa.model.request.NewsQueryRequest;

import java.util.List;

/**
 * @author Created by huangxb on 2018-08-15 11:00:39
 *
 */
public interface JpaNewsService{
    /**
     * 查询新闻详情
     * @param s 新闻编号
     * @return 新闻详情
     */
    JpaNewsInfoModel queryOne(String s);

    /**
     * 查询所有新闻
     * @return 新闻列表
     */
    List<JpaNewsInfoModel> listAll(NewsQueryRequest request);

    /**
     * 添加一条新闻
     * @param addBy 添加人
     * @param saveModel 新闻信息
     */
    void insert(NewsSaveModel saveModel,Integer addBy);
}
