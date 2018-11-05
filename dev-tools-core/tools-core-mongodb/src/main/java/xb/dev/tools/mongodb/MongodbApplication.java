package xb.dev.tools.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Created by huangxb
 * @date 2018-10-19 10:03:55
 */
@SpringBootApplication
@EnableFeignClients("xb.dev.tools.api.es")
@ComponentScan(basePackages = {"xb.dev.tools.api.es","xb.dev.tools.mongodb","xb.dev.tools.mongodb.task"})
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }
}
