package com.directa24.main.challenge;

import com.directa24.main.challenge.model.http.Director;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/directors")
public class DirectorsController {

    private final MoviesService moviesService;

    public DirectorsController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<Director> getDirectorsOrdered(@RequestParam(defaultValue = "3") Integer threshold) {
        return this.moviesService.getDirectorsOrdered(threshold);
    }
}
