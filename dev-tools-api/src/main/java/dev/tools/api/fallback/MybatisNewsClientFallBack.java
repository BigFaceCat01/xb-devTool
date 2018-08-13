package dev.tools.api.fallback;

import dev.tools.api.model.NewsEntity;
import dev.tools.api.model.NewsModel;
import dev.tools.api.model.Result;
import dev.tools.api.provider.MybatisNewsClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-13 09:33:46
 * @Description:
 */
@Service
public class MybatisNewsClientFallBack implements MybatisNewsClient {
    @Override
    public Result<Boolean> insertNews(NewsModel newsModel) {
        return Result.build("-1","调用insertNews服务失败");
    }

    @Override
    public Result<List<NewsEntity>> queryAllNews() {
        return Result.build("-1","调用queryAllNews服务失败");
    }
}
