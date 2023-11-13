package com.abhishek.reactive.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class MainRouter {

    @Autowired
    private RapidAPIRouter rapidAPIRouter;
    @Autowired
    private BingNewsRouter bingNewsRouter;



    @Bean
    public RouterFunction<ServerResponse> mainRouterFunction() {
        return RouterFunctions.route()
                .path("/api/v1", builder -> builder
                        .add(rapidAPIRouter.planetRouters())
                        .add( bingNewsRouter.bingNewsRouter())
                )
                .build();
    }

}
