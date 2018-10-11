package xb.dev.tools.tool.jpa.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.dao.entity.QJpaNewsEntity;
import xb.dev.tools.dao.entity.QJpaUserEntity;
import xb.dev.tools.model.JpaNewsInfoModel;
import xb.dev.tools.tool.jpa.service.JpaNewsService;

/**
 * @author Created by huangxb on 2018-08-15 11:01:16
 *
 */
@Service
public class JpaNewsServiceImpl implements JpaNewsService {
    @Autowired
    private JPAQueryFactory factory;


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


}
