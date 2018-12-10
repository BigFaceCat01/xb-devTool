package xb.dev.tools.jpa.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xb.dev.tools.jpa.dao.entity.JpaNewsEntity;

import java.util.Date;
import java.util.List;

/**
 * @author Created by huangxb on 2018-08-15 11:04:28
 * @Description:
 */
public interface JpaNewsRepository extends JpaRepository<JpaNewsEntity,Integer> {
    @Query(value = "select SUBSTRING_INDEX(news_id,'0',1) from xb_news where create_time < ?1",nativeQuery = true)
    List<String> testQuery(Date time);
}
