package xb.dev.tools.es.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xb.dev.tools.common.PageModule;
import xb.dev.tools.common.Result;
import xb.dev.tools.es.code.CodeEnum;
import xb.dev.tools.es.dao.entity.EsNewsEntity;
import xb.dev.tools.es.model.HSCodeModel;
import xb.dev.tools.es.service.EsNewsService;

import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-10-12 11:23:48
 */
@RestController
@RequestMapping("/es/news")
public class EsNewsController {
    @Autowired
    private EsNewsService esNewsService;

    @ApiOperation(value = "同步mongodb新闻记录到es",httpMethod = "PUT")
    @PutMapping("")
    public Result<Void> insert(){
        esNewsService.insert(null);
        return Result.build(CodeEnum.SUCCESS.getCode());
    }

    @ApiOperation(value = "同步海关编码记录到es",httpMethod = "PUT")
    @PutMapping("hscode")
    public Result<Void> hscode(){
        esNewsService.syncHSCode();
        return Result.build(CodeEnum.SUCCESS.getCode());
    }

    @ApiOperation(value = "同步news.163.com新闻记录到es",httpMethod = "PUT")
    @PutMapping("news163")
    public Result<Void> insertFromNews163(){
        esNewsService.syncNews163com();
        return Result.build(CodeEnum.SUCCESS.getCode());
    }

    @ApiOperation(value = "根据标识查询新闻",httpMethod = "GET")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "path",dataType = "string",name = "id",value = "标识")
    )
    @GetMapping("{id}")
    public Result<EsNewsEntity> queryById(@PathVariable("id") String id){
        return Result.build(CodeEnum.SUCCESS.getCode(),esNewsService.getById(id));
    }

    @ApiOperation(value = "根据标题，来源，分类标签查询新闻",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "title", value = "标题"),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "source", value = "来源"),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "type", value = "分类标签")
        })
    @GetMapping("search")
    public Result<List<EsNewsEntity>> listBy(String title, String source, String type){
        return Result.build(CodeEnum.SUCCESS.getCode(),esNewsService.listBy(title,source,type));
    }

    @ApiOperation(value = "查询海关编码",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "每页数量"),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "name", value = "名称")
    })
    @GetMapping("hscode")
    public Result<PageModule<HSCodeModel>> listBy(Integer pageNum, Integer pageSize, String name){
        return Result.build(CodeEnum.SUCCESS.getCode(),esNewsService.queryHSCode(pageNum,pageSize,name));
    }
}
