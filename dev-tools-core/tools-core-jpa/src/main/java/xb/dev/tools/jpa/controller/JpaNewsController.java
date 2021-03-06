package xb.dev.tools.jpa.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xb.dev.tools.common.Result;
import xb.dev.tools.jpa.code.CodeEnum;
import xb.dev.tools.jpa.model.JpaNewsInfoModel;
import xb.dev.tools.jpa.service.JpaNewsService;

/**
 * @author Created by huangxb
 * @date 2018-10-11 14:59:21
 */
@RestController
@RequestMapping("/jpa")
public class JpaNewsController {

    @Autowired
    private JpaNewsService jpaNewsService;

    @ApiOperation(value = "查询新闻详情",httpMethod = "GET")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "path",dataType = "string",name = "sn",value = "新闻标识")
    )
    @GetMapping("news/{sn}")
    public Result<JpaNewsInfoModel> queryInfoBySn(@PathVariable("sn") String sn){
        return Result.build(CodeEnum.SUCCESS.getCode(),jpaNewsService.queryOne(sn));
    }
}
