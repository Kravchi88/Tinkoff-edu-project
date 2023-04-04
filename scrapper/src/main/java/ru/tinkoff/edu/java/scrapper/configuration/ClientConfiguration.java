package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.webClients.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webClients.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.webClients.httpClients.HttpGitHubClient;
import ru.tinkoff.edu.java.scrapper.webClients.httpClients.HttpStackOverfowClient;

@Configuration
public class ClientConfiguration {
    @Bean
    GitHubClient gitHubClient(ApplicationConfig applicationConfig) {
        if (applicationConfig.baseUrlGitHub().isBlank()) {
            return new HttpGitHubClient(WebClient.builder());
        }
        return new HttpGitHubClient(WebClient.builder(), applicationConfig.baseUrlGitHub());
    }

    @Bean
    StackOverflowClient stackOverflowClient(ApplicationConfig applicationConfig) {
        if (applicationConfig.baseUrlStackOverflow().isBlank()) {
            return new HttpStackOverfowClient(WebClient.builder());
        }
        return new HttpStackOverfowClient(WebClient.builder(), applicationConfig.baseUrlStackOverflow());
    }
}
