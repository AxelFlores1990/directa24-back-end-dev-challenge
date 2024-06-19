package com.directa24.main.challenge;

import com.directa24.main.challenge.model.http.Director;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "30000")
@ExtendWith(SpringExtension.class)
public class DirectorsControllerIT {

    @Autowired
    protected WebTestClient webClient;

    @Test
    void testGetDirectorsOrdered() {
        this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/directors")
                        .queryParam("threshold", "4")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Director.class)
                .hasSize(2)
                .contains(new Director("Martin Scorsese"), new Director("Woody Allen"));
    }
}