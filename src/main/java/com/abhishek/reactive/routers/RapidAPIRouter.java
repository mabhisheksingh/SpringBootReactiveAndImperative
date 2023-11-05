package com.abhishek.reactive.routers;

import com.abhishek.reactive.handlers.PlanetsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * https://rapidapi.com/newbAPIOfficial/api/planets-info-by-newbapi/
 */
@Configuration
public class RapidAPIRouter {

    @Autowired
    private PlanetsHandler planetsHandler;

    public RouterFunction<ServerResponse> planetRouters() {
        return RouterFunctions.route()
                .GET("/testPlanet",req -> ServerResponse.ok().bodyValue("testing Passed RouterFunction is working fine. :)"))
                .path("/planets", builder -> builder.nest(accept(MediaType.ALL), v1Routes -> v1Routes
                        .GET("", planetsHandler::getAllPlanets)
                        .GET("/{id}", planetsHandler::getSinglePlanets))
                ).filter( (request , next ) ->{
                    System.out.println("ENTER");
                    return next.handle(request);

                })
                .build();
    }
}
