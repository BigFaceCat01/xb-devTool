package xb.dev.tools.tool.mybatis.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xb.dev.tools.base.BaseController;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.common.Result;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.model.NewsModel;
import xb.dev.tools.tool.mybatis.service.MybatisNewsService;
import xb.dev.tools.utils.CodeUtil;
import xb.dev.tools.utils.JsonUtil;

import java.util.Date;

/**
 * @Author: Created by huangxb on 2018-08-08 14:08:49
 * @Description:
 */
@RestController
@RequestMapping("/mybatis/news")
public class MybatisNewsController extends BaseController {
    @Autowired
    private MybatisNewsService mybatisNewsService;

    @ApiOperation(value = "添加一条新闻-huangxb",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",dataType = "NewsModel",name = "newsModel",value = "新闻信息")
    })
    @PostMapping("")
    public Result<Boolean> insertNews(@RequestBody NewsModel newsModel){
        NewsEntity newsEntity = JsonUtil.beanConvert(newsModel,NewsEntity.class);
        newsEntity.setNewsId(CodeUtil.getNewsId());
        newsEntity.setAuthor("AAA");
        newsEntity.setBrowseCount(0L);
        newsEntity.setCreateTime(new Date());
        newsEntity.setSupportCount(0L);
        newsEntity.setStatus((byte)0);
        newsEntity.setOpposeCount(0L);
        try {
            mybatisNewsService.insertNews(newsEntity);
            return Result.build(CodeEnum.SUCCESS.getCode(),true);
        } catch (XbServiceException e) {
            e.printStackTrace();
            return Result.build(CodeEnum.FAILED.getCode(),false);
        }
    }

}
