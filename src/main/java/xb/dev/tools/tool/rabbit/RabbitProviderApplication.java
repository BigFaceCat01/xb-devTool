package xb.dev.tools.tool.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Created by huangxb on 2018-08-07 19:44:44
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"xb.dev.tools.tool.rabbit.*"})
public class RabbitProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(RabbitProviderApplication.class);
    }
}
