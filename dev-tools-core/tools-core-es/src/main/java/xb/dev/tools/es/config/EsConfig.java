package xb.dev.tools.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Created by huangxb on 2018-08-03 17:49:38
 *
 */
@Configuration
public class EsConfig {
    @Value("127.0.0.1")
    private String esHost;
    @Value("9200")
    private int esPort;
    @Value("elasticsearch")
    private String esClusterName;
    @Bean
    public RestHighLevelClient client() throws Exception{
        RestHighLevelClient client=new RestHighLevelClient(RestClient.builder(new HttpHost(esHost,esPort,"http")));
        return client;
    }

}
