package xb.dev.tools.mongo.service;

import org.springframework.stereotype.Service;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.mongo.model.NewsModel;

/**
 * @Author: Created by huangxb on 2018-08-01 18:11:33
 * @Description:
 */
public interface NewsService {
    /**
     * 新增一个新闻
     * @param newsModel
     */
    public void insertNews(NewsModel newsModel) throws XbServiceException;
}
