package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Value("${name.service.serverHost}")
    private String serverLocalhost;

    @Value("${name.service.userHost}")
    private  String userLocalhost;

    @Bean
    public RestClient restClientQuizCreate(){
        return RestClient.create(serverLocalhost);
    }

    @Bean
    public RestClient restUserClient(){
        return RestClient.create(userLocalhost);
    }

}
