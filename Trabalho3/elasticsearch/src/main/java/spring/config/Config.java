package spring.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

public class Config {
    @Bean
    public static RestHighLevelClient clientConfiguration() {
        ClientConfiguration clientConfiguration =
                ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        System.out.println("Connected to Elasticsearch on port 9200");
        return RestClients.create(clientConfiguration)
                .rest();
    }
}
