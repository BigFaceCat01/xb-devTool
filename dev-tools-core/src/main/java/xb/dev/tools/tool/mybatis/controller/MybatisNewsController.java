package xb.dev.tools.tool.mybatis.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xb.dev.document.temp.ExcelUtils;
import xb.dev.document.temp.TranslateData;
import xb.dev.tools.Result;
import xb.dev.tools.base.BaseController;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.model.NewsInsertModel;
import xb.dev.tools.tool.mybatis.service.MybatisNewsService;
import xb.dev.tools.utils.CodeUtil;
import xb.dev.tools.utils.JsonUtil;

import java.io.InputStream;
import java.util.*;

/**
 * @Author: Created by huangxb on 2018-08-08 14:08:49
 * @Description:
 */
@RestController
@RequestMapping("/mybatis/news")
public class MybatisNewsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(MybatisNewsController.class);
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

    @ApiOperation(value = "excel转换",httpMethod = "POST")
    @PostMapping(value = "excel",consumes = "multipart/*",headers = "content-type=multipart/form-data")
    public Result<Map<String,Map<String,Map<String,String>>>> excelExchange(@ApiParam(value = "文件",required = true) MultipartFile file){
        Map<String,List<TranslateData>> map = null;
        try {
            InputStream is = file.getInputStream();
            map = ExcelUtils.excelParse(TranslateData.class,is,logger);

        int count = 0;
        Map<String,Map<String,Map<String,String>>> api = new HashMap<>();
        if(map!=null){
            Set<String> keys = map.keySet();
            for(String key:keys){
                Map<String,String> enMap = new HashMap<>();
                Map<String,String> cnMap = new HashMap<>();
                Map<String,Map<String,String>> result = new HashMap<>();
                List<TranslateData> res = map.get(key);
                for(TranslateData translateData:res) {
                    if(translateData.getBrief()!=null&&!translateData.getBrief().equals("")) {
                        enMap.put(translateData.getBrief(), translateData.getEn());
                        cnMap.put(translateData.getBrief(), translateData.getCn());
                    }
                }
                result.put("moduleCn",cnMap);
                result.put("moduleEn",enMap);
                api.put("module"+count,result);
                count++;
            }
            String s =JSON.toJSONString(api);
            System.out.println();
        }
        return Result.build(CodeEnum.SUCCESS.getCode(),api);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(CodeEnum.SUCCESS.getCode(),e.getMessage());
        }
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
