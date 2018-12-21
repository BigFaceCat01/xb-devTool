package xb.dev.tools.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xb.dev.tools.jpa.dao.entity.JpaNewsEntity;
import xb.dev.tools.jpa.dao.repository.JpaNewsRepository;
import xb.dev.tools.jpa.model.NewsSaveModel;
import xb.dev.tools.jpa.util.JsonUtil;

import java.util.Date;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-21 17:37:49
 */
@Service
public class NewsUpsertService {

    @Autowired
    private JpaNewsRepository jpaNewsRepository;

    @CacheEvict(value = "news::all",allEntries = true)
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
