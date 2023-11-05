package com.abhishek.reactive.handlers;

import com.abhishek.dto.PlanetRouterdto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class PlanetsHandler {

    private static WebClient webClient;//= WebClient.create("https://planets-info-by-newbapi.p.rapidapi.com/api/v1/planets");

    @Value("${keys.plnates.X-RapidAPI-Key}")
    private String xRapidKey;

    @Value("${keys.plnates.X-RapidAPI-Host}")
    private String xRapidHost;

    static {
        webClient = WebClient.builder().baseUrl("https://planets-info-by-newbapi.p.rapidapi.com/api/v1/planets")
                .build();
    }
    public Mono<ServerResponse> getSinglePlanets(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<PlanetRouterdto> res = webClient.get()
                .uri("/" + id)
                .header("X-RapidAPI-Key", xRapidKey)
                .header("X-RapidAPI-Host", xRapidHost)
                .retrieve()
                .bodyToMono(PlanetRouterdto.class);
        return res
                .flatMap(planet -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(planet))
                .onErrorResume( e -> Mono.just(" Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue(s)));
    }

    public Mono<ServerResponse> getAllPlanets(ServerRequest request) {
        Mono<List<PlanetRouterdto>> res = webClient.get()
                .uri("/")
                .header("X-RapidAPI-Key", xRapidKey)
                .header("X-RapidAPI-Host", xRapidHost)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PlanetRouterdto>>() { });
//                .bodyToMono(PlanetRouterdto.class)

        return res
                .flatMap(planet -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(planet))
                .onErrorResume( e -> Mono.just(" Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN).bodyValue(s)));
    }
}
