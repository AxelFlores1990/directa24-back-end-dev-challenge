package com.directa24.main.challenge;

import com.directa24.main.challenge.model.client.PaginatedMovie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MoviesClient {

    private final String moviesSearchResource;
    private final WebClient webClient;

    public MoviesClient(WebClient webClient, @Value("${web.resource.directa24.movies.search}") String moviesSearchResource) {
        this.moviesSearchResource = moviesSearchResource;
        this.webClient = webClient;
    }

    public Mono<PaginatedMovie> getPage(int page) {
        return this.webClient.get()
                .uri(moviesSearchResource, page)
                .retrieve()
                .bodyToMono(PaginatedMovie.class);
    }
}
