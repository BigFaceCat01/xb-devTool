package xb.dev.tools.tool.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @Author: Created by huangxb on 2018-08-15 11:53:17
 * @Description:
 */
//@NoRepositoryBean
//@Configuration
public class DataSourceRepository {
    //选择数据源
   /* @PersistenceContext(unitName = "slaveEntityManagerFactoryBean")
    protected EntityManager slaveManager;*/
    @PersistenceContext(unitName = "primaryEntityManagerFactoryBean")
    protected EntityManager primaryManager;

    @Bean(name = "primaryJPAQueryFactory")
    public JPAQueryFactory getPrimaryJPAQueryFactory(){
        return new JPAQueryFactory(primaryManager);
    }

   /* @Bean(name = "slaveJPAQueryFactory")
    public JPAQueryFactory getSlaveJPAQueryFactory(){
        return new JPAQueryFactory(slaveManager);
    }*/
}
