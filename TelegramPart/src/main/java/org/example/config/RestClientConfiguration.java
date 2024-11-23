package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Value("${name.service.localhost}")
    private String serverLocalhost;

    @Bean
    public RestClient restClientDataBase(){
        return RestClient.create(serverLocalhost);
    }

}
