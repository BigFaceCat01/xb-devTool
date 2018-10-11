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
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.common.CodeEnum;
import xb.dev.tools.constant.EsConstant;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.es.service.EsNewsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Created by huangxb on 2018-08-03 18:01
 * @Description:
 */
@Service
public class EsNewsServiceImpl implements EsNewsService {

    @Autowired
    private RestHighLevelClient client;


    public List<NewsEntity> queryAll() throws XbServiceException {
        String author = null;
        String title = null;
        String releaseDate = null;
        SearchRequest searchRequest = new SearchRequest(EsConstant.NEWS_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder builder= QueryBuilders.boolQuery();

        if(author!=null) {
            builder.filter(QueryBuilders.fuzzyQuery("author", author + "*"));
        }

        if(title!=null) {
            builder.filter(QueryBuilders.fuzzyQuery("title", title + "?"));
        }

        if(releaseDate!=null) {
            builder.filter(QueryBuilders.termQuery("releaseDate", releaseDate));
        }

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
    public void insert(NewsEntity newsEntity) throws XbServiceException {
        String newsJson= JSON.toJSONStringWithDateFormat(newsEntity,EsConstant.DATE_FORMAT);
        IndexRequest indexRequest = new IndexRequest(EsConstant.NEWS_INDEX,EsConstant.NEWS_TYPE,newsEntity.getNewsId()).source(newsJson,XContentType.JSON);
        try {
            client.index(indexRequest);

        } catch (IOException e) {
            throw new XbServiceException("新闻保存异常,cause by "+e.getMessage(),e);
        }
    }

    @Override
    public void delete(String id) throws XbServiceException {
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
    public void update(NewsEntity newsEntity) throws XbServiceException {

    }

    @Override
    public Set<String> suggest(String keywords) {
        SearchRequest searchRequest = new SearchRequest(EsConstant.NEWS_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SuggestionBuilder completionSuggestionBuilder = SuggestBuilders.completionSuggestion("keywords.suggest").text(keywords);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggest_keywords",completionSuggestionBuilder);
        searchSourceBuilder.suggest(suggestBuilder).size(0);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest);
        } catch (IOException e) {
            throw new XbServiceException(CodeEnum.SUGGEST_NEWS_FAILD.getCode(),CodeEnum.SUGGEST_NEWS_FAILD.getChDesc(),e);
        }
        Suggest suggest = searchResponse.getSuggest();
        CompletionSuggestion completionSuggestion = suggest.getSuggestion("suggest_keywords");
        List<CompletionSuggestion.Entry> entries = completionSuggestion.getEntries();

        Set<String> suggestTexts = new HashSet<>();
        for(CompletionSuggestion.Entry entry:entries){
            List<CompletionSuggestion.Entry.Option> options = entry.getOptions();
            for (CompletionSuggestion.Entry.Option option : options) {
                String suggestText = option.getText().string();
                suggestTexts.add(suggestText);
            }
        }
        return suggestTexts;
    }

    @Override
    public void deleteWithLogic(String s) {

    }
}
