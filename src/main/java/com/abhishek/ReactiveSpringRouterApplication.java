package com.abhishek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

// import reactor.core.publisher.Hooks;

@SpringBootApplication
public class ReactiveSpringRouterApplication {

    @Bean
    public WebProperties.Resources resources(){
        return new WebProperties.Resources();
    }

    public static void main(String[] args) {
        // ** Use for debugging purpose
//        Hooks.onOperatorDebug();
        SpringApplication.run(ReactiveSpringRouterApplication.class, args);
    }
}
