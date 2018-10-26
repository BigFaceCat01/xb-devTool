package xb.dev.tools.es.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
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
import xb.dev.document.DocumentUtil;
import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.tools.common.PageModule;
import xb.dev.tools.es.code.CodeEnum;
import xb.dev.tools.es.constant.EsConstant;
import xb.dev.tools.es.dao.entity.EsNewsEntity;
import xb.dev.tools.es.exception.XbServiceException;
import xb.dev.tools.es.model.HSCodeModel;
import xb.dev.tools.es.service.EsNewsService;
import xb.dev.tools.es.task.DataGrabTask;
import xb.dev.tools.es.task.DataSaveTask;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Created by huangxb on 2018-08-03 18:01
 *
 */
@Service
@Slf4j
public class EsNewsServiceImpl implements EsNewsService {
//    @Autowired
//    private MongoNewsService mongoNewsService;

    @Autowired
    private RestHighLevelClient client;


    public List<EsNewsEntity> queryAll() {
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
        List<EsNewsEntity> newsEntityList=new ArrayList<>();
        for(SearchHit s:hits){
            String ss=s.getSourceAsString();
            EsNewsEntity b=JSON.parseObject(ss,EsNewsEntity.class);
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
//        PageModule<MongoNewsListModel> result = mongoNewsService.listUserNewsForPage(1,100,2422736121541824512L);
//        PageModule<MongoNewsListModel> result = null;
//        result.getData().forEach(item->{
//            EsNewsEntity esNews = JsonUtil.beanConvert(item,EsNewsEntity.class);
        esNewsEntity.setUserId(2422736121541824512L);
            String json = JSON.toJSONString(esNewsEntity);
            //设置存储到搜索引擎的index，type，这里使用自定义id，不使用es生成的id
            indexRequest.index(EsConstant.ES_NEWS_INDEX)
                    .type(EsConstant.ES_NEWS_TYPE)
                    .source(json,XContentType.JSON);
            try {
                //保存
                client.index(indexRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        });
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

    @Override
    public void syncNews163com() {
        ArrayBlockingQueue<EsNewsEntity> dataQueue = new ArrayBlockingQueue<>(100);
        Thread save = new Thread(new DataSaveTask(dataQueue,this));
        Thread grab = new Thread(new DataGrabTask(dataQueue,save));
        save.start();
        grab.start();
    }

    @Override
    public void syncHSCode() {
        IndexRequest indexRequest = new IndexRequest();
        try {
            Map<String,List<HSCodeModel>> result =  DocumentUtil.getDataByPath(HSCodeModel.class,"D:/HSCode.xls");
            List<HSCodeModel> codeModels = result.get("DOCUMENT");
            codeModels.forEach(item->{
                String json = JSON.toJSONString(item);
                    indexRequest.index(EsConstant.ES_HS_CODE_INDEX)
                            .type(EsConstant.ES_HS_CODE_TYPE)
                            .source(json,XContentType.JSON);
                try {
                    //保存
                    client.index(indexRequest);
                    log.info("[保存:]{}",json);
                } catch (IOException e) {
                    log.warn("[失败:]{}",json);
                }
        });
        } catch (DocumentHandlerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageModule<HSCodeModel> queryHSCode(Integer pageNum,Integer pageSize,String name) {
        if(pageNum == null || pageNum <0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize <0){
            pageSize = 10;
        }
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        searchSourceBuilder.query(QueryBuilders.matchQuery("name",name));
        int from = pageNum -1;
        int size = pageSize;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchRequest.source(searchSourceBuilder);

        List<HSCodeModel> list = new ArrayList<>();
        try {
            //返回结果
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            for(SearchHit hit:searchHits){
                HSCodeModel model = JSON.parseObject(hit.getSourceAsString(),HSCodeModel.class);
                model.setId(hit.getId());
                list.add(model);
            }
            return new PageModule<>(searchResponse.getHits().totalHits,pageSize,pageNum,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
