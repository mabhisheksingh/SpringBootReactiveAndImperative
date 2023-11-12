package com.abhishek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// import reactor.core.publisher.Hooks;

@SpringBootApplication
public class ReactiveSpringRouterApplication {

// //    @Bean
// //    WebClient.Builder getWebClientBuilder(){
// //        return WebClient.builder();
// //    }
//     @Autowired
//     static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // ** Use for debugging purpose
//        Hooks.onOperatorDebug();

        SpringApplication.run(ReactiveSpringRouterApplication.class, args);

    }
}
