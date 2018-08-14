package xb.dev.tools.tool.es.service;

import xb.dev.tools.base.BaseService;

import java.util.Set;

/**
 * @Author: Created by huangxb on 2018-08-03 18:03
 * @Description:
 */
public interface EsNewsService extends BaseService {
    /**
     * 搜索建议
     * @param keywords 搜索的关键词
     * @return
     */
    public Set<String> suggest(String keywords);

}
