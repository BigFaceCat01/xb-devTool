package xb.dev.tools.tool.jpa.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.common.Result;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.jpa.service.JpaNewsService;

import java.util.List;

/**
 * @author Created by huangxb on 2018-08-15 11:01:16
 *
 */
@Service
public class JpaNewsServiceImpl implements JpaNewsService {
//    @Autowired
//    private JPAQueryFactory factory;


    @Override
    public NewsEntity queryOne(String s) {
        return null;
    }

    @Override
    public void insert(NewsEntity newsEntity) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void deleteWithLogic(String s) {

    }

    @Override
    public void update(NewsEntity newsEntity) {

    }
}
