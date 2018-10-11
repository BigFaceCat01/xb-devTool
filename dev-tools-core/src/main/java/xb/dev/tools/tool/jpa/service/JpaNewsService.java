package xb.dev.tools.tool.jpa.service;

import xb.dev.tools.model.JpaNewsInfoModel;

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
}
