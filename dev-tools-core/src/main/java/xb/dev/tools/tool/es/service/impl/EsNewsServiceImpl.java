package xb.dev.tools.tool.es.service.impl;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
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
import xb.dev.tools.common.PageModule;
import xb.dev.tools.constant.EsConstant;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.dao.entity.es.EsNewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.model.MongoNewsListModel;
import xb.dev.tools.tool.es.service.EsNewsService;
import xb.dev.tools.tool.mongo.service.MongoNewsService;
import xb.dev.tools.utils.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Created by huangxb on 2018-08-03 18:01
 *
 */
@Service
public class EsNewsServiceImpl implements EsNewsService {
    @Autowired
    private MongoNewsService mongoNewsService;

    @Autowired
    private RestHighLevelClient client;


    public List<NewsEntity> queryAll() {
        String author = null;
        String title = null;
        String releaseDate = null;
        SearchRequest searchRequest = new SearchRequest(EsConstant.ES_NEWS_INDEX);
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
    public Set<String> suggest(String keywords) {
        SearchRequest searchRequest = new SearchRequest(EsConstant.ES_NEWS_INDEX);
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
    public void insert(EsNewsEntity esNewsEntity) {
        IndexRequest indexRequest = new IndexRequest();
        //获得mongodb中的100条数据
        PageModule<MongoNewsListModel> result = mongoNewsService.listUserNewsForPage(1,100,2422736121541824512L);
        result.getData().forEach(item->{
            EsNewsEntity esNews = JsonUtil.beanConvert(item,EsNewsEntity.class);
            esNews.setUserId(2422736121541824512L);
            String json = JSON.toJSONString(esNews);
            //设置存储到搜索引擎的index，type，这里使用自定义id，不使用es生成的id
            indexRequest.index(EsConstant.ES_NEWS_INDEX)
                    .type(EsConstant.ES_NEWS_TYPE)
                    .source(json,XContentType.JSON)
                    .id(esNews.getNewsId());
            try {
                //保存
                client.index(indexRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public EsNewsEntity getById(String id) {
        GetRequest getRequest = new GetRequest();
        //设置index，type，id
        getRequest.index(EsConstant.ES_NEWS_INDEX)
                .type(EsConstant.ES_NEWS_TYPE)
                .id(id);
        try {
            //获得响应结果
            GetResponse getResponse = client.get(getRequest);
            String result = getResponse.getSourceAsString();
            return JSON.parseObject(result,EsNewsEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EsNewsEntity> listBy(String title, String source, String type) {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //创建多条件逻辑查询，逻辑或
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(source!=null) {
            boolQueryBuilder.should(QueryBuilders.termQuery("source", source));
        }
        if(title != null) {
            boolQueryBuilder.should(QueryBuilders.termQuery("title", title));
        }
        if(type!=null) {
            boolQueryBuilder.should(QueryBuilders.termQuery("type", type));
        }

        searchSourceBuilder.query(boolQueryBuilder);

        searchRequest.source(searchSourceBuilder);
        List<EsNewsEntity> list = new ArrayList<>();
        try {
            //返回结果
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            for(SearchHit hit:searchHits){
                list.add(JSON.parseObject(hit.getSourceAsString(),EsNewsEntity.class));
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
