package xb.dev.tools.tool.es.service.impl;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.constant.EsConstant;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.es.service.EsNewsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author: Created by huangxb on 2018-08-03 18:01
 * @Description:
 */
@Service
public class EsNewsServiceImpl implements EsNewsService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public List<NewsEntity> queryAll() throws XbServiceException {
        String author = null;
        String title = null;
        String releaseDate = null;
        SearchRequest searchRequest = new SearchRequest(EsConstant.NEWS_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder builder= QueryBuilders.boolQuery();

        if(author!=null)
            builder.filter(QueryBuilders.fuzzyQuery("author",author+"*"));

        if(title!=null)
            builder.filter(QueryBuilders.fuzzyQuery("title",title+"?"));

        if(releaseDate!=null)
            builder.filter(QueryBuilders.termQuery("releaseDate",releaseDate));

        System.out.println(builder);

        searchSourceBuilder.query(builder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;

        try {
            searchResponse = client.search(searchRequest);
        } catch (IOException e) {
            throw new XbServiceException("新闻搜索异常,cause by "+e.getMessage(),e);
        }

        SearchHits searchHits = searchResponse.getHits();
        long total = searchHits.totalHits;
        SearchHit[] hits=searchHits.getHits();
        List<NewsEntity> newsEntityList=new ArrayList<NewsEntity>();
        for(SearchHit s:hits){
            String ss=s.getSourceAsString();
            NewsEntity b=JSON.parseObject(ss,NewsEntity.class);
            newsEntityList.add(b);
        }
        return newsEntityList;
    }

    @Override
    public void insertNews(NewsEntity newsEntity) throws XbServiceException {
        String newsJson= JSON.toJSONStringWithDateFormat(newsEntity,EsConstant.DATE_FORMAT);
        IndexRequest indexRequest = new IndexRequest(EsConstant.NEWS_INDEX,EsConstant.NEWS_TYPE,newsEntity.getNewsId()).source(newsJson,XContentType.JSON);
        try {
            client.index(indexRequest);
        } catch (IOException e) {
            throw new XbServiceException("新闻保存异常,cause by "+e.getMessage(),e);
        }
    }

    @Override
    public void deleteNews(String id) throws XbServiceException {
        DeleteRequest deleteRequest = new DeleteRequest(EsConstant.NEWS_INDEX,EsConstant.NEWS_TYPE,id);
        try {
            client.delete(deleteRequest);
        } catch (IOException e) {
            throw new XbServiceException("新闻删除异常,cause by "+e.getMessage(),e);
        }
    }

    @Override
    public NewsEntity queryOne(String id) throws XbServiceException {
        return null;
    }

    @Override
    public void updateNews(NewsEntity newsEntity) throws XbServiceException {

    }
}
