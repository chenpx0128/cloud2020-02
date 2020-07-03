package springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
   // @LoadBalanced //由于手写负载均衡所以要先注释掉
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
