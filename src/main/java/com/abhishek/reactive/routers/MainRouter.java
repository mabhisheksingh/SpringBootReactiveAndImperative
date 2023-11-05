package com.abhishek.reactive.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

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

//                .path("/**", e -> e.nest(
//                        accept(MediaType.ALL), req ->  req
//                                .GET( "", q -> ServerResponse.ok().bodyValue("NOT FOUND"))
//                                .POST( "", q -> ServerResponse.ok().bodyValue("NOT FOUND"))
//                                .PATCH( "", q -> ServerResponse.ok().bodyValue("NOT FOUND"))
//                                .OPTIONS( "", q -> ServerResponse.ok().bodyValue("NOT FOUND"))
//                                .DELETE( "", q -> ServerResponse.ok().bodyValue("NOT FOUND"))
//                                .PUT( "", q -> ServerResponse.ok().bodyValue("NOT FOUND"))
//                                .HEAD( "", q -> ServerResponse.ok().bodyValue("NOT FOUND"))
//                ))
                .build();
    }

}
