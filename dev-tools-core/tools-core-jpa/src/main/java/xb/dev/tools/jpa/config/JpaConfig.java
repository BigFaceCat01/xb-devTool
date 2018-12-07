package xb.dev.tools.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author huang xiao bao
 * @date 2018-12-7 15:13
 * jpa配置
 */
@Configuration
public class JpaConfig {
    @Bean
    public JPAQueryFactory getJPAQueryFactory(EntityManager entityManager){
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        return queryFactory;
    }
}
