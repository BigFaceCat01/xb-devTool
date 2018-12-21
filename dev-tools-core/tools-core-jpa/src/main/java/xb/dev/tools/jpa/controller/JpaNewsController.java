package xb.dev.tools.jpa.controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xb.dev.tools.common.Result;
import xb.dev.tools.jpa.code.CodeEnum;
import xb.dev.tools.jpa.dao.entity.QJpaNewsEntity;
import xb.dev.tools.jpa.model.JpaNewsInfoModel;
import xb.dev.tools.jpa.model.NewsSaveModel;
import xb.dev.tools.jpa.model.request.NewsQueryRequest;
import xb.dev.tools.jpa.service.JpaNewsService;
import xb.dev.tools.jpa.service.impl.NewsUpsertService;

import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-10-11 14:59:21
 */
@RestController
@RequestMapping("/jpa")
public class JpaNewsController {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private JpaNewsService jpaNewsService;
    @Autowired
    private NewsUpsertService newsUpsertService;

    @ApiOperation(value = "查询新闻详情",httpMethod = "GET")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "path",dataType = "string",name = "sn",value = "新闻标识")
    )
    @GetMapping("news/{sn}")
    public Result<JpaNewsInfoModel> queryInfoBySn(@PathVariable("sn") String sn){
        return Result.build(CodeEnum.SUCCESS.getCode(),jpaNewsService.queryOne(sn));
    }
    @ApiOperation(value = "查询新闻详情",httpMethod = "GET")
    @GetMapping("test")
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> queryInfoBySnT(){
        testTra();
        return Result.build("0");
    }
    @ApiOperation(value = "新增新闻")
    @ApiImplicitParam(paramType = "body" , dataType = "NewsSaveModel",name = "saveModel",value = "新闻信息")
    @PostMapping("")
    public Result<Void> insert(@RequestBody NewsSaveModel saveModel){
        newsUpsertService.insert(saveModel,1);
        return Result.build(CodeEnum.SUCCESS.getCode());
    }

    @ApiOperation(value = "查询所有新闻")
    @GetMapping("")
    public Result<List<JpaNewsInfoModel>> listAll(NewsQueryRequest request){
        return Result.build(CodeEnum.SUCCESS.getCode(), jpaNewsService.listAll(request));
    }

    private void testTra(){
        QJpaNewsEntity qJpaNewsEntity = QJpaNewsEntity.jpaNewsEntity;
        jpaQueryFactory.update(qJpaNewsEntity)
                .setNull(qJpaNewsEntity.title)
                .where(qJpaNewsEntity.id.eq(6))
                .execute();
    }


}
