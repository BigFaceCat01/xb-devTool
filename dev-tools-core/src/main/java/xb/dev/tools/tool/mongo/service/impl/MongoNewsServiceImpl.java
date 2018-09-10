package xb.dev.tools.tool.mongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.common.Result;
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
    public Result<List<NewsEntity>> queryAll() throws XbServiceException {
        return null;
    }

    @Override
    public Result<NewsEntity> queryOne(String id) throws XbServiceException {

        return null;
    }

    @Override
    public Result<Boolean> insert(NewsEntity newsEntity) throws XbServiceException {
        try {
            newsRepository.insert(newsEntity);
            return Result.build(CodeEnum.SUCCESS.getCode(),true);
        }catch (Exception e){
            throw new XbServiceException("新闻添加错误，cause:"+e.getMessage(),e);
        }
    }

    @Override
    public Result<Boolean> delete(String id) throws XbServiceException {
        return null;
    }

    @Override
    public Result<Boolean> update(NewsEntity newsEntity) throws XbServiceException {
        return null;
    }
}
