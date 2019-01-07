package xb.dev.tools.jpa.service.impl;

import com.alibaba.fastjson.JSON;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.dev.tools.jpa.JpaApplication;
import xb.dev.tools.jpa.dao.repository.JpaNewsRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-07 14:20:56
 */
@Slf4j
@SpringBootTest(classes = JpaApplication.class)
@RunWith(SpringRunner.class)
public class JpaNewsServiceImplTest {
    @Autowired
    private JpaNewsRepository jpaNewsRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Test
    public void testQueryAnotation() {
        List<String> strings = jpaNewsRepository.testQuery(new Date());
        System.out.println();
    }

    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
////        calendar.add(Calendar.MONTH,1);
//        calendar.add(Calendar.MONTH,-1);
//        Date expire = DateUtils.getSpecifiedDayFromNow(DateUtils.TYPE_AFTER, 30);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
//        NewsQueryRequest newsQueryRequest = new NewsQueryRequest();
//        System.out.println(JSON.toJSONString(newsQueryRequest));
        String s = null;
        System.out.println(JSON.toJSONString(s));
    }
}