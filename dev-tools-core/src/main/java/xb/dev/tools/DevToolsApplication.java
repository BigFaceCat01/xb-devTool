package xb.dev.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("xb.dev")
public class DevToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevToolsApplication.class, args);
    }
}
