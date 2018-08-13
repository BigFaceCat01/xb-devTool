package dev.tools.api.provider;

import dev.tools.api.fallback.MybatisNewsClientFallBack;
import dev.tools.api.model.NewsEntity;
import dev.tools.api.model.NewsModel;
import dev.tools.api.model.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-13 09:26:58
 * @Description:
 */
@FeignClient(value = "xb-devTools-dev-provider",fallback = MybatisNewsClientFallBack.class)
public interface MybatisNewsClient {

    @PostMapping("/mybatis/news")
    public Result<Boolean> insertNews(@RequestBody NewsModel newsModel);

    @GetMapping("")
    public Result<List<NewsEntity>> queryAllNews();
}
