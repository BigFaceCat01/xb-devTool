package xb.dev.tools.tool.jpa.service.impl;

import org.springframework.stereotype.Service;
import xb.dev.tools.common.Result;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.jpa.service.JpaNewsService;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-15 11:01:16
 * @Description:
 */
@Service
public class JpaNewsServiceImpl implements JpaNewsService {
//    @Autowired
//    private JPAQueryFactory factory;


    @Override
    public Result<List<NewsEntity>> queryAll() throws XbServiceException {
        return null;
    }

    @Override
    public Result<NewsEntity> queryOne(String s) throws XbServiceException {
        return null;
    }

    @Override
    public Result<Boolean> insert(NewsEntity newsEntity) throws XbServiceException {
        return null;
    }

    @Override
    public Result<Boolean> delete(String s) throws XbServiceException {
        return null;
    }

    @Override
    public Result<Boolean> update(NewsEntity newsEntity) throws XbServiceException {
        return null;
    }
}
