package xb.dev.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: Created by huangxb on 2018-9-12 10:25.
 * @description: swagger配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    public Swagger2Config() {
    }
    @Bean
    public Docket createRestApi(){
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("xb.dev")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo(){

            return new ApiInfoBuilder().title("api文档中心").description("简单优雅restfun风格").version("1.0").build();
    }
    @Bean
    public UiConfiguration uiConfig(){
        return new UiConfiguration(null,"list","alpha","schema",UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,false,true,60000L);

    }

}



