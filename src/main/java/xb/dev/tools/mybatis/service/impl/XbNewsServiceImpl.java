package xb.dev.tools.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.mybatis.base.BaseMapper;
import xb.dev.tools.mybatis.dao.entity.XbNewsEntity;
import xb.dev.tools.mybatis.service.XbNewsService;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-02 14:00:21
 * @Description:
 */
@Service
public class XbNewsServiceImpl implements XbNewsService {
    @Autowired
    private BaseMapper baseMapper;

    @Override
    public List<XbNewsEntity> queryAll() throws XbServiceException {
        try {
            return baseMapper.findForList("XbNewsMapper.queryAll",null);
        }catch (Exception e){
            throw new XbServiceException("查找所有新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    public void insertNews(XbNewsEntity xbNewsEntity) throws XbServiceException {
        try {
            baseMapper.insert("XbNewsMapper.insert", xbNewsEntity);
        }catch (Exception e){
            throw new XbServiceException("添加新闻出错,cause by:"+e.getMessage(),e);
        }
    }
}
