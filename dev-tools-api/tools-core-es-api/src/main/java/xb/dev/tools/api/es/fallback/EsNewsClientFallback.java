package xb.dev.tools.api.es.fallback;

import org.springframework.stereotype.Service;
import xb.dev.tools.api.es.entity.EsNewsEntity;
import xb.dev.tools.api.es.provider.EsNewsClient;
import xb.dev.tools.common.Result;

import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-10-24 09:55:21
 */
@Service
public class EsNewsClientFallback implements EsNewsClient {
    @Override
    public Result<List<EsNewsEntity>> listBy(String title, String source, String type) {
        return Result.build("-1","调用EsNewsClient-listBy失败");
    }
}
