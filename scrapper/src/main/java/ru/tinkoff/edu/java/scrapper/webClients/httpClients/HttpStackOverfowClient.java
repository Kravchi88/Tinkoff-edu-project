package ru.tinkoff.edu.java.scrapper.webClients.httpClients;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.linkParser.records.StackOverflowInfo;
import ru.tinkoff.edu.java.scrapper.webClients.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.webClients.dto.StackOverflowResponse;

public class HttpStackOverfowClient implements StackOverflowClient {
    private final WebClient webClient;

    public HttpStackOverfowClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.stackexchange.com").build();
    }

    public HttpStackOverfowClient(WebClient.Builder webClientBuilder, String url) {
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    @Override
    public StackOverflowResponse fetchInfo(StackOverflowInfo info) {
        return webClient.get().uri("/2.3/questions/{id}?site=stackoverflow", info.id())
                .retrieve().bodyToMono(StackOverflowResponse.class).block();
    }
}
