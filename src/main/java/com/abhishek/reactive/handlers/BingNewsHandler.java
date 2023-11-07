package com.abhishek.reactive.handlers;

import com.abhishek.dto.PlanetRouterdto;
import com.abhishek.model.bingnews.BingNewsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BingNewsHandler {

    private WebClient webClient;//= WebClient.create("https://bing-news-search1.p.rapidapi.com/news");

    @Value("${keys.bing-news.X-BingApis-SDK}")
    private Boolean bingRapidAPI;

    @Value("${keys.bing-news.X-RapidAPI-Key}")
    private String xRapidKey;

    @Value("${keys.bing-news.X-RapidAPI-Host}")
    private String xRapidHost;

    BingNewsHandler(WebClient.Builder webClient){
        this.webClient = webClient.baseUrl("https://bing-news-search1.p.rapidapi.com/news").build();
    }


    public Mono<ServerResponse> newsCategory(ServerRequest request) {
        System.out.println("REQ "+request);
        Mono<BingNewsDTO> res = webClient.get()
                .header("X-RapidAPI-Key", xRapidKey)
                .header("X-BingApis-SDK", String.valueOf(bingRapidAPI))
                .header("X-RapidAPI-Host", xRapidHost)
                .retrieve()
                .bodyToMono(BingNewsDTO.class);
        return res
                .flatMap(news -> {
                    System.out.println("NEWS "+news );
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(news);

                })
                .onErrorResume( e -> Mono.just(" Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue(s)));
    }

    public Mono<ServerResponse> newsSearch(ServerRequest request) {
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

    public Mono<ServerResponse> newsTrending(ServerRequest request) {
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

}
