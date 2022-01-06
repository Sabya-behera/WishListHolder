package com.example.Like_and_Unlike_feature.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig
{
    @Bean
        public RestTemplate restTemplate()
        {
            return new RestTemplate();
        }

}

