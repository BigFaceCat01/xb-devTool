package xb.dev.tools.tool.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.base.BaseMapper;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.sender.NewsMqSender;
import xb.dev.tools.tool.mybatis.service.MybatisNewsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by huangxb on 2018-08-02 14:00:21
 * @Description:
 */
@Service
public class MybatisNewsServiceImpl implements MybatisNewsService {
    @Autowired
    private BaseMapper baseMapper;
    @Autowired
    private NewsMqSender newsMqSender;

    @Override
    public List<NewsEntity> queryAll() throws XbServiceException {
        try {
            return baseMapper.findForList("XbNewsMapper.queryAll",null);
        }catch (Exception e){
            throw new XbServiceException("查找所有新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    public void deleteNews(String id) throws XbServiceException {
        Map<String,Object> map = new HashMap<>();
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
    public void insertNews(NewsEntity newsEntity) throws XbServiceException {
        try {
            baseMapper.insert("XbNewsMapper.insertNews", newsEntity);
            newsMqSender.sendNewsInsert(newsEntity);
        }catch (Exception e){
            throw new XbServiceException("添加新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    public void updateNews(NewsEntity newsEntity) throws XbServiceException {

    }
}
