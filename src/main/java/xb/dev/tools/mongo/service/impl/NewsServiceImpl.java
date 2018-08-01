package xb.dev.tools.mongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.mongo.dao.entity.NewsEntity;
import xb.dev.tools.mongo.dao.repository.NewsRepository;
import xb.dev.tools.mongo.model.NewsModel;
import xb.dev.tools.mongo.service.NewsService;
import xb.dev.tools.utils.JsonUtil;

/**
 * @Author: Created by huangxb on 2018-08-01 18:12:07
 * @Description:
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public void insertNews(NewsModel newsModel) throws XbServiceException{
        try {
            NewsEntity newsEntity = JsonUtil.beanConvert(newsModel, NewsEntity.class);
            newsRepository.insert(newsEntity);
        }catch (Exception e){
            throw new XbServiceException("新闻添加错误，cause:"+e.getMessage(),e);
        }
    }
}
