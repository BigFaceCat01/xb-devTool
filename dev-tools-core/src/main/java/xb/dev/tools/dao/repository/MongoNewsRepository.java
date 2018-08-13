package xb.dev.tools.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xb.dev.tools.dao.entity.NewsEntity;

/**
 * @Author: Created by huangxb on 2018-08-01 18:09:34
 * @Description: 使用mongo实现新闻数据操作基本操作
 */
public interface MongoNewsRepository extends MongoRepository<NewsEntity,String> {
}
