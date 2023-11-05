package com.abhishek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
// import reactor.core.publisher.Hooks;

@SpringBootApplication
public class ReactiveSpringRouterApplication {

    @Bean
    WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }

    public static void main(String[] args) {
        // ** Use for debugging purpose
//        Hooks.onOperatorDebug();
        SpringApplication.run(ReactiveSpringRouterApplication.class, args);
    }
}
