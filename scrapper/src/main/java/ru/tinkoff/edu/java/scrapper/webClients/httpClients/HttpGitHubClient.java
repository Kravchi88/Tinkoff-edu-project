package ru.tinkoff.edu.java.scrapper.webClients.httpClients;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.linkParser.records.GitHubInfo;
import ru.tinkoff.edu.java.scrapper.webClients.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webClients.dto.GitHubResponse;

public class HttpGitHubClient implements GitHubClient {
    private final WebClient webClient;

    public HttpGitHubClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public HttpGitHubClient(WebClient.Builder webClientBuilder, String url) {
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    @Override
    public GitHubResponse fetchInfo(GitHubInfo info) {
        return this.webClient.get().uri("/repos/{name}/{repo}", info.userName(), info.repositoryName())
                .retrieve().bodyToMono(GitHubResponse.class).block();
    }
}
