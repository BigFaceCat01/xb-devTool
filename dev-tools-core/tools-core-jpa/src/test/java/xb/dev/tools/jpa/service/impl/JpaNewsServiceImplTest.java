package xb.dev.tools.jpa.service.impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.dialect.Dialect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.dev.tools.jpa.JpaApplication;
import xb.dev.tools.jpa.dao.entity.JpaNewsEntity;
import xb.dev.tools.jpa.dao.entity.QJpaNewsEntity;
import xb.dev.tools.jpa.dao.repository.JpaNewsRepository;
import xb.dev.tools.jpa.util.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

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
    public void queryOne() {
        QJpaNewsEntity qJpaNewsEntity = QJpaNewsEntity.jpaNewsEntity;
        List<JpaNewsEntity> fetch = jpaQueryFactory.select(qJpaNewsEntity)
                .from(qJpaNewsEntity)
                .where(qJpaNewsEntity.createTime.loe(new Date()))
                .fetch();
        System.out.println();
    }

    @Test
    public void testQueryAnotation() {
        List<String> strings = jpaNewsRepository.testQuery(new Date());
        System.out.println();
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH,1);
        calendar.add(Calendar.MONTH,-1);
        Date expire = DateUtils.getSpecifiedDayFromNow(DateUtils.TYPE_AFTER, 30);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
    }
}