package xb.dev.tools.tool.mongo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xb.dev.tools.base.BaseController;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.common.PageModule;
import xb.dev.tools.common.Result;
import xb.dev.tools.model.MongoNewsBasicInfo;
import xb.dev.tools.model.MongoNewsInsertModel;
import xb.dev.tools.model.MongoNewsListModel;
import xb.dev.tools.model.mongo.MongoNewsModel;
import xb.dev.tools.tool.mongo.service.MongoNewsService;
import xb.dev.tools.utils.JsonUtil;

import javax.validation.Valid;

/**
 * @author Created by huangxb
 * @date 2018-09-25 17:52:27
 */
@CrossOrigin(origins = "http://localhost:19091", maxAge = 3600)
@RestController
@RequestMapping("/mongo")
public class MongoNewsController extends BaseController {
    @Autowired
    private MongoNewsService mongoNewsService;

    @ApiOperation(value = "添加一个新闻到mongodb上-huangxb",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",dataType = "MongoNewsInsertModel",name = "insertModel",value = "新闻信息")
    })
    @PostMapping("news")
    public Result<Void> insert(@RequestBody @Valid MongoNewsInsertModel insertModel){

        MongoNewsModel mongoNewsModel = JsonUtil.beanConvert(insertModel,MongoNewsModel.class);
        mongoNewsModel.setUserId(localUser.get());
        mongoNewsService.insert(mongoNewsModel);

        return Result.build(CodeEnum.SUCCESS.getCode());
    }

    @ApiOperation(value = "添加一个新闻到mongodb上-huangxb",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",dataType = "MongoNewsInsertModel",name = "insertModel",value = "新闻信息")
    })
    @PostMapping("news/confirm")
    public Result<Void> confirm(@RequestBody @Valid MongoNewsInsertModel insertModel){

        MongoNewsModel mongoNewsModel = JsonUtil.beanConvert(insertModel,MongoNewsModel.class);
        mongoNewsModel.setUserId(localUser.get());
        mongoNewsService.confirm(mongoNewsModel);

        return Result.build(CodeEnum.SUCCESS.getCode());
    }

    @ApiOperation(value = "查询用户的新闻列表-huangxb",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "int",name = "pageNum",value = "页数"),
            @ApiImplicitParam(paramType = "query",dataType = "int",name = "pageSize",value = "每页数量")
    })
    @GetMapping("news")
    public Result<PageModule<MongoNewsListModel>> insert(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Long userId = localUser.get();
        return Result.build(CodeEnum.SUCCESS.getCode(),mongoNewsService.listUserNewsForPage(pageNum,pageSize,userId));
    }

    @ApiOperation(value = "根据id获取新闻详情-huangxb",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",dataType = "String",name = "newsSn",value = "页数")
    })
    @GetMapping("news/{newsSn}")
    public Result<MongoNewsBasicInfo> insert(@PathVariable("newsSn") String newsSn){
        return Result.build(CodeEnum.SUCCESS.getCode(),mongoNewsService.getById(newsSn));
    }

    @ApiOperation(value = "同步网易新闻到mongodb-huangxb",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "byte",name = "type",value = "类型,1-国内,2-航空")
    })
    @GetMapping("news/syncNews163com")
    public Result<Void> syncNews163com(@RequestParam("type") Byte type){
        mongoNewsService.syncNews163com(type);
        return Result.build(CodeEnum.SUCCESS.getCode());
    }
}
