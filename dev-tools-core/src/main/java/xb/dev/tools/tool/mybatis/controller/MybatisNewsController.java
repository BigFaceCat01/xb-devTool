package xb.dev.tools.tool.mybatis.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xb.dev.tools.base.BaseController;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.common.Result;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.model.NewsInsertModel;
import xb.dev.tools.tool.mybatis.service.MybatisNewsService;
import xb.dev.tools.utils.CodeUtil;
import xb.dev.tools.utils.JsonUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<Boolean> insertNews(@RequestBody NewsInsertModel newsModel){
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

    @ApiOperation(value = "测试接口-huangxb",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",dataType = "int[]",name = "ids",value="整数数组"),
            @ApiImplicitParam(paramType = "query",dataType = "string",name = "name",value = "名称")
    })
    @PostMapping("test")
    public Result<Map<String,Object>> test(@RequestBody Integer[] ids, @RequestParam String name){
        Map<String,Object> param = new HashMap<>();
        param.put("ids",ids);
        param.put("name",name);
        return Result.build(CodeEnum.SUCCESS.getCode(),param);
    }


    @ApiOperation(value = "查询所有新闻-huangxb",httpMethod = "GET")
    @GetMapping("")
    public Result<List<NewsEntity>> queryAllNews(){
        try {
            List<NewsEntity> apis = mybatisNewsService.queryAll();
            return Result.build(CodeEnum.SUCCESS.getCode(),apis);
        } catch (XbServiceException e) {
            e.printStackTrace();
            return Result.build(CodeEnum.FAILED.getCode(),CodeEnum.FAILED.getChDesc());
        }
    }

    @ApiOperation(value = "根据id查询新闻-huangxb",httpMethod = "GET")
    @GetMapping("{newsId}")
    public Result<NewsEntity> queryNewsById(@PathVariable("newsId") String id){
        try {
            NewsEntity api = mybatisNewsService.queryOne(id);
            return Result.build(CodeEnum.SUCCESS.getCode(),api);
        } catch (XbServiceException e) {
            e.printStackTrace();
            return Result.build(CodeEnum.FAILED.getCode(),CodeEnum.FAILED.getChDesc());
        }
    }

}
