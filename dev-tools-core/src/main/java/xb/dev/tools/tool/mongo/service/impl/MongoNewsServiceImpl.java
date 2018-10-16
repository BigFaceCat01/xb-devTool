package xb.dev.tools.tool.mongo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import xb.dev.tools.common.PageModule;
import xb.dev.tools.constant.MongoConstant;
import xb.dev.tools.constant.News163CategoryEnum;
import xb.dev.tools.constant.NewsContants;
import xb.dev.tools.model.MongoNewsBasicInfo;
import xb.dev.tools.model.MongoNewsListModel;
import xb.dev.tools.model.mongo.MongoNewsModel;
import xb.dev.tools.tool.mongo.service.MongoNewsService;
import xb.dev.tools.utils.HttpUtil;
import xb.dev.tools.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author: Created by huangxb on 2018-08-01 18:12:07
 *
 */
@Service
public class MongoNewsServiceImpl implements MongoNewsService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MongoNewsModel queryOne(String s) {
        Query query = new Query();
        query.addCriteria(Criteria.where("newsId").is(s));

        return mongoTemplate.findOne(query,MongoNewsModel.class);

    }

    @Override
    public void insert(MongoNewsModel newsModel) {
        //临时设置作者名称
        newsModel.setAuthor("admin");
        newsModel.setBrowseCount(NewsContants.NEWS_ZERO);
        newsModel.setCreateTime(new Date());
        newsModel.setDeleteFlag(NewsContants.NEWS_NOT_DELETE);
        newsModel.setOpposeCount(NewsContants.NEWS_ZERO);
        newsModel.setStatus(NewsContants.WAIT_CONFIRM);
        newsModel.setSupportCount(NewsContants.NEWS_ZERO);

        mongoTemplate.insert(newsModel);
    }

    @Override
    public void confirm(MongoNewsModel newsModel) {
        //临时设置作者名称
        newsModel.setAuthor("admin");
        newsModel.setBrowseCount(NewsContants.NEWS_ZERO);
        newsModel.setCreateTime(new Date());
        newsModel.setDeleteFlag(NewsContants.NEWS_NOT_DELETE);
        newsModel.setOpposeCount(NewsContants.NEWS_ZERO);
        newsModel.setStatus(NewsContants.CHECKING);
        newsModel.setSupportCount(NewsContants.NEWS_ZERO);

        mongoTemplate.insert(newsModel);
    }

    @Override
    public void delete(String s) {
        Query query = new Query();
        query.addCriteria(Criteria.where("newsId").is(s));

        mongoTemplate.remove(query,MongoNewsModel.class);
    }

    @Override
    public void deleteWithLogic(String s) {
        //查询出该新闻
        Query query = new Query();
        query.addCriteria(Criteria.where("newsId").is(s));
        //修改新闻删除标志为true
        Update update = new Update();
        update.set("deleteFlag",NewsContants.NEWS_DELETE);
        update.set("updateTime",new Date());

        mongoTemplate.updateFirst(query,update,MongoNewsModel.class);
    }

    @Override
    public void update(MongoNewsModel newsModel) {

    }

    @Override
    public PageModule<MongoNewsListModel> listUserNewsForPage(Integer pageNum, Integer pageSize, Long userId) {
        if(pageNum==null||pageNum<=0){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize = 10;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,"createTime"));
        query.with(sort);
        long offset = (pageNum-1)*pageSize;
        query.skip(offset);
        query.limit(pageSize);
        List<MongoNewsModel> lists = mongoTemplate.find(query,MongoNewsModel.class);
        List<MongoNewsListModel> re = new ArrayList<>(pageSize);
        lists.forEach(item->re.add(JsonUtil.beanConvert(item,MongoNewsListModel.class)));
        long totalSize = mongoTemplate.count(query,MongoNewsModel.class);

        return new PageModule<>(totalSize,pageSize,pageNum,re);
    }

    @Override
    public MongoNewsBasicInfo getById(String id) {

        MongoNewsModel mongoNewsModel = mongoTemplate.findOne(Query.query(Criteria.where("newsId").is(id)),MongoNewsModel.class);

        MongoNewsBasicInfo basicInfo = JsonUtil.beanConvert(mongoNewsModel,MongoNewsBasicInfo.class);
        return basicInfo;
    }

    @Override
    public void syncNews163com(Byte type) {
        String url = MongoConstant.NEWS_163_COM_URL+MongoConstant.NEWS_163_COM_DEFAULT_TOKEN+"/"+News163CategoryEnum.getName(type)+".js?callback=data_callback%20HTTP/1.1";
        String result = HttpUtil.getContentFromUrl(url);
        String json = result.substring(0,result.length()-1).substring(result.indexOf("["));
        JSONArray jsonObject = JSON.parseArray(json);
        System.out.println(jsonObject);

    }
}
