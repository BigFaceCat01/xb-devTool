package xb.dev.tools.jpa.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xb.dev.tools.jpa.dao.entity.JpaNewsEntity;
import xb.dev.tools.jpa.dao.entity.QJpaNewsEntity;
import xb.dev.tools.jpa.dao.entity.QJpaUserEntity;
import xb.dev.tools.jpa.dao.repository.JpaNewsRepository;
import xb.dev.tools.jpa.model.JpaNewsInfoModel;
import xb.dev.tools.jpa.model.NewsSaveModel;
import xb.dev.tools.jpa.model.request.NewsQueryRequest;
import xb.dev.tools.jpa.service.JpaNewsService;
import xb.dev.tools.jpa.util.JsonUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Created by huangxb on 2018-08-15 11:01:16
 *
 */
@Service
public class JpaNewsServiceImpl implements JpaNewsService {
    @Autowired
    private JPAQueryFactory factory;
    @Autowired
    private JpaNewsRepository jpaNewsRepository;

    @Override
    public JpaNewsInfoModel queryOne(String s) {
        QJpaNewsEntity qJpaNewsEntity = QJpaNewsEntity.jpaNewsEntity;
        QJpaUserEntity qJpaUserEntity = QJpaUserEntity.jpaUserEntity;
        return factory.select(Projections.bean(JpaNewsInfoModel.class,
                qJpaNewsEntity.id,
                qJpaNewsEntity.newsId,
                qJpaUserEntity.userName.as("author"),
                qJpaNewsEntity.body,
                qJpaNewsEntity.browseCount,
                qJpaNewsEntity.createTime,
                qJpaNewsEntity.deleteFlag,
                qJpaNewsEntity.opposeCount,
                qJpaNewsEntity.source,
                qJpaNewsEntity.status,
                qJpaNewsEntity.supportCount,
                qJpaNewsEntity.title,
                qJpaNewsEntity.type,
                qJpaNewsEntity.userId))
                .from(qJpaNewsEntity)
                .leftJoin(qJpaUserEntity)
                .on(qJpaUserEntity.userId.eq(qJpaNewsEntity.userId))
                .where(qJpaNewsEntity.newsId.eq(s))
                .fetchOne();
    }

    @Override
    @Cacheable(value = "news::all",key="#root.args[0]")
    public List<JpaNewsInfoModel> listAll(NewsQueryRequest request) {
        QJpaNewsEntity qJpaNewsEntity = QJpaNewsEntity.jpaNewsEntity;
        QJpaUserEntity qJpaUserEntity = QJpaUserEntity.jpaUserEntity;
        long offset = (request.getPageNum() - 1) * request.getPageSize();
        return factory.select(Projections.bean(JpaNewsInfoModel.class,
                qJpaNewsEntity.id,
                qJpaNewsEntity.newsId,
                qJpaUserEntity.userName.as("author"),
                qJpaNewsEntity.body,
                qJpaNewsEntity.browseCount,
                qJpaNewsEntity.createTime,
                qJpaNewsEntity.deleteFlag,
                qJpaNewsEntity.opposeCount,
                qJpaNewsEntity.source,
                qJpaNewsEntity.status,
                qJpaNewsEntity.supportCount,
                qJpaNewsEntity.title,
                qJpaNewsEntity.type,
                qJpaNewsEntity.userId))
                .from(qJpaNewsEntity)
                .leftJoin(qJpaUserEntity)
                .on(qJpaUserEntity.userId.eq(qJpaNewsEntity.userId))
                .offset(offset)
                .limit(request.getPageSize())
                .fetch();
    }

    @Override
    @CacheEvict(value = "news::all")
    @Transactional(rollbackFor = Exception.class)
    public void insert(NewsSaveModel saveModel, Integer addBy) {
        JpaNewsEntity entity = JsonUtil.beanConvert(saveModel,JpaNewsEntity.class);
        entity.setOpposeCount(0L);
        entity.setSupportCount(0L);
        entity.setBrowseCount(0L);
        entity.setAuthor("admin");
        entity.setStatus((byte) 0);
        entity.setUserId(addBy);
        entity.setCreateTime(new Date());
        entity.setDeleteFlag((byte) 0);
        entity.setNewsId("XB20181221"+Math.rint(1231123));
        jpaNewsRepository.save(entity);
    }
}
