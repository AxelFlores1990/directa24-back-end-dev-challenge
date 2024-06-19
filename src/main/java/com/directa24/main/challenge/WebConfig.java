package com.directa24.main.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.util.MimeTypeUtils.parseMimeType;

@Configuration
public class WebConfig {

    @Value("${web.resource.directa24.baseUrl}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(this.baseUrl)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(c -> c.customCodecs().decoder(this.jackson2JsonDecoder()))
                                .build())
                .build();
    }

    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public Jackson2JsonDecoder jackson2JsonDecoder() {
        return new Jackson2JsonDecoder(this.objectMapper(), parseMimeType(APPLICATION_OCTET_STREAM_VALUE));
    }
}