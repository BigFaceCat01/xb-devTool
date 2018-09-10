package xb.dev.tools.tool.mongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.dao.repository.MongoNewsRepository;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.mongo.service.MongoNewsService;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-01 18:12:07
 * @Description:
 */
@Service
public class MongoNewsServiceImpl implements MongoNewsService {
    @Autowired
    private MongoNewsRepository newsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<NewsEntity> queryAll() throws XbServiceException {
        return null;
    }

    @Override
    public NewsEntity queryOne(String id) throws XbServiceException {

        return null;
    }

    @Override
    public void insertNews(NewsEntity newsEntity) throws XbServiceException {
        try {
            newsRepository.insert(newsEntity);
        }catch (Exception e){
            throw new XbServiceException("新闻添加错误，cause:"+e.getMessage(),e);
        }
    }

    @Override
    public void deleteNews(String id) throws XbServiceException {

    }

    @Override
    public void updateNews(NewsEntity newsEntity) throws XbServiceException {

    }
}
