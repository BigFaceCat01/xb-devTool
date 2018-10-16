package xb.dev.tools.tool.es.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.common.Result;
import xb.dev.tools.dao.entity.es.EsNewsEntity;
import xb.dev.tools.tool.es.service.EsNewsService;
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
}
