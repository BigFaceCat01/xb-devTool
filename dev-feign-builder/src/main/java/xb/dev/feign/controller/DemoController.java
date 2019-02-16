package xb.dev.feign.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xb.dev.feign.annotation.FeignComponent;
import xb.dev.feign.annotation.FeignInclude;
import xb.dev.feign.model.ServiceModel;
import xb.dev.feign.model.TestModel;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-01 11:25:28
 */
@RestController
@RequestMapping("demo")
@FeignComponent
public class DemoController {

    @ApiOperation(value = "demo",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",dataType = "string",name="demoId",value = "demo id")
    })
    @GetMapping("{demoId}")
    @FeignInclude(applicationName = "xb-demo-provider")
    public TestModel<ServiceModel> demoIndex(@PathVariable("demoId") String demoId){
        System.out.println(demoId);
        return null;
    }
}
