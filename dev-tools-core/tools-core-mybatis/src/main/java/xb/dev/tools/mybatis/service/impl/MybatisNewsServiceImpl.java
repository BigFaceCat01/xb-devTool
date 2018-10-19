package xb.dev.tools.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xb.dev.tools.mybatis.base.BaseMapper;
import xb.dev.tools.mybatis.dao.entity.NewsEntity;
import xb.dev.tools.mybatis.exception.XbServiceException;
import xb.dev.tools.mybatis.service.MybatisNewsService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Created by huangxb on 2018-08-02 14:00:21
 *
 */
@Service
public class MybatisNewsServiceImpl implements MybatisNewsService {
    @Autowired
    private BaseMapper baseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws XbServiceException {
        Map<String,Object> map = new HashMap<>(16);
        map.put("newsId",id);
        map.put("deleteFlag",true);
        try {
            baseMapper.insert("XbNewsMapper.deleteNewsById", map);

        }catch (Exception e){
            throw new XbServiceException("删除新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    public NewsEntity queryOne(String id) throws XbServiceException {

        return baseMapper.findForObject("XbNewsMapper.queryById",id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(NewsEntity newsEntity) throws XbServiceException {
        try {
            baseMapper.insert("XbNewsMapper.insertNews", newsEntity);

        }catch (Exception e){
            throw new XbServiceException("添加新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsEntity newsEntity) throws XbServiceException {

    }

    @Override
    public void deleteWithLogic(String s) {

    }
}
