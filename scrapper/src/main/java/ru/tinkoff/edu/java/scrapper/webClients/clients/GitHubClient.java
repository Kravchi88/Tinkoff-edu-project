package ru.tinkoff.edu.java.scrapper.webClients.clients;

import ru.tinkoff.edu.java.linkParser.records.GitHubInfo;
import ru.tinkoff.edu.java.scrapper.webClients.dto.GitHubResponse;

public interface GitHubClient {
    GitHubResponse fetchInfo(GitHubInfo info);
}
