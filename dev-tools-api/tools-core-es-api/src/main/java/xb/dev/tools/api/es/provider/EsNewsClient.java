package xb.dev.tools.api.es.provider;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xb.dev.tools.api.es.entity.EsNewsEntity;
import xb.dev.tools.api.es.fallback.EsNewsClientFallback;
import xb.dev.tools.common.Result;

import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-10-24 09:54:42
 */
@FeignClient(value = "dev-tools-es",fallback = EsNewsClientFallback.class)
public interface EsNewsClient {

    @GetMapping("es/news/search")
    Result<List<EsNewsEntity>> listBy(@RequestParam String title, @RequestParam String source, @RequestParam String type);
}
