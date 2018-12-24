package xb.dev.tools.jpa.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xb.dev.tools.jpa.JpaApplication;
import xb.dev.tools.jpa.dao.entity.JpaNewsEntity;
import xb.dev.tools.jpa.dao.entity.QJpaNewsEntity;
import xb.dev.tools.jpa.dao.repository.JpaNewsRepository;

import java.util.*;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-07 14:20:56
 */
@SpringBootTest(classes = JpaApplication.class)
@RunWith(SpringRunner.class)
public class JpaNewsServiceImplTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private JpaNewsRepository jpaNewsRepository;
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void queryOne() {
        QJpaNewsEntity qJpaNewsEntity = QJpaNewsEntity.jpaNewsEntity;
//        List<JpaNewsEntity> fetch = jpaQueryFactory.select(qJpaNewsEntity)
//                .from(qJpaNewsEntity)
//                .where(qJpaNewsEntity.createTime.loe(new Date()))
//                .fetch();
//        System.out.println();
//        List<JpaNewsEntity> fetch = jpaQueryFactory.selectFrom(qJpaNewsEntity)
//                .where(qJpaNewsEntity.newsId.startsWith("AB"))
//                .fetch();
        JpaNewsEntity jpaNewsEntity = jpaQueryFactory.selectFrom(qJpaNewsEntity).where(qJpaNewsEntity.id.eq(6)).fetchOne();
        String s = null;
        jpaQueryFactory.update(qJpaNewsEntity)
                .setNull(qJpaNewsEntity.title)
                .where(qJpaNewsEntity.id.eq(6))
                .execute();
        System.out.println();
    }

    @Test
    public void testQueryAnotation() {
        List<String> strings = jpaNewsRepository.testQuery(new Date());
        System.out.println();
    }

    private static final Map<String,String> HEADER= new TreeMap<>((o1,o2) -> 1);
    static {
        //定义品牌头部
        HEADER.put("brandId","品牌编号");
        HEADER.put("brandName","品牌名称");
        HEADER.put("brandAlias","品牌别名");
        HEADER.put("corpName","企业名称");
        HEADER.put("status","品牌状态");
        HEADER.put("confirmTime","发布时间");
        HEADER.put("checkTime","审核时间");
        HEADER.put("checkName","审核人");
        HEADER.put("checkStatus","审核状态");
    }

    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
////        calendar.add(Calendar.MONTH,1);
//        calendar.add(Calendar.MONTH,-1);
//        Date expire = DateUtils.getSpecifiedDayFromNow(DateUtils.TYPE_AFTER, 30);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
//        NewsQueryRequest newsQueryRequest = new NewsQueryRequest();
//        System.out.println(JSON.toJSONString(newsQueryRequest));
        Set<String> head = HEADER.keySet();
        System.out.println();
    }
}