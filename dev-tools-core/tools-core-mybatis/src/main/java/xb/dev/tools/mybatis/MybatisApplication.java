package xb.dev.tools.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Created by huangxb
 * @date 2018-10-19 11:11:55
 */
@EnableTransactionManagement
@MapperScan({"xb.dev.tools.mybatis.mapper"})
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
