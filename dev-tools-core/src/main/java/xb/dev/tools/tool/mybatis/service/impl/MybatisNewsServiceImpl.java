package xb.dev.tools.tool.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.base.BaseMapper;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.common.Result;
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
    public Result<List<NewsEntity>> queryAll() throws XbServiceException {
        try {
            return Result.build(CodeEnum.SUCCESS.getCode(),baseMapper.findForList("XbNewsMapper.queryAll",null));
        }catch (Exception e){
            throw new XbServiceException("查找所有新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    public Result<Boolean> delete(String id) throws XbServiceException {
        Map<String,Object> map = new HashMap<>();
        map.put("newsId",id);
        map.put("deleteFlag",true);
        try {
            baseMapper.insert("XbNewsMapper.deleteNewsById", map);
            return Result.build(CodeEnum.SUCCESS.getCode(),true);
        }catch (Exception e){
            throw new XbServiceException("删除新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    public Result<NewsEntity> queryOne(String id) throws XbServiceException {

        return Result.build(CodeEnum.SUCCESS.getCode(),baseMapper.findForObject("XbNewsMapper.queryById",id));
    }

    @Override
    public Result<Boolean> insert(NewsEntity newsEntity) throws XbServiceException {
        try {
            baseMapper.insert("XbNewsMapper.insertNews", newsEntity);
            newsMqSender.sendNewsInsert(newsEntity);
            return Result.build(CodeEnum.SUCCESS.getCode(),true);
        }catch (Exception e){
            throw new XbServiceException("添加新闻出错,cause by:"+e.getMessage(),e);
        }
    }

    @Override
    public Result<Boolean> update(NewsEntity newsEntity) throws XbServiceException {
        return null;
    }
}
