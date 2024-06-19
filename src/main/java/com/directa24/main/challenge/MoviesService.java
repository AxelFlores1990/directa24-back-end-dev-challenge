package com.directa24.main.challenge;

import com.directa24.main.challenge.model.Movie;
import com.directa24.main.challenge.model.http.Director;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MoviesService {

    private final MoviesClient moviesClient;

    public MoviesService(MoviesClient moviesClient) {
        this.moviesClient = moviesClient;
    }

    public Flux<Director> getDirectorsOrdered(Integer minMovies) {
        return this.moviesClient.getPage(1)
                .expand(page -> {
                    if (page.getPage() > page.getTotalPages()) {
                        return Mono.empty();
                    }

                    return this.moviesClient.getPage(page.getPage() + 1);

                })
                .flatMap(pages -> Flux.fromIterable(pages.getData()))
                .groupBy(Movie::getDirector)
                .flatMap(directorGroup -> directorGroup.count().map(c -> c > minMovies ? directorGroup.key() : ""))
                .filter(director -> !director.isEmpty())
                .sort(String::compareTo)
                .map(Director::new);
    }
}
