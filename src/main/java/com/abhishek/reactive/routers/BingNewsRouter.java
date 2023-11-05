package com.abhishek.reactive.routers;
/*
Bing BingNews Search
https://rapidapi.com/microsoft-azure-org-microsoft-cognitive-services/api/bing-news-search1
 */

import com.abhishek.reactive.handlers.BingNewsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class BingNewsRouter {

    @Autowired
    private BingNewsHandler bingNewsHandler;

    public RouterFunction<ServerResponse> bingNewsRouter(){
        return RouterFunctions.route()
                .GET("/testBingNews",req -> ServerResponse.ok().bodyValue("testing Passed Bing Router is working fine. :)"))
                .path("/bing-news", builder ->
                        builder.nest( RequestPredicates.method(HttpMethod.GET),
                                routes ->routes
                                        .GET("" , bingNewsHandler::newsCategory)
                                        .GET("/search" ,bingNewsHandler::newsSearch )
                                        .GET("/trendingtopics" , bingNewsHandler::newsTrending)
                                        .build()
                        ).onError( Exception.class, (throwable, request) ->
                                ServerResponse.badRequest().bodyValue(throwable.getMessage()))
                )
                .build();
    }
}
