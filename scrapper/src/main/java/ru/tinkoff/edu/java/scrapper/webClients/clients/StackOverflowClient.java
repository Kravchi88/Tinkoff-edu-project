package ru.tinkoff.edu.java.scrapper.webClients.clients;

import ru.tinkoff.edu.java.linkParser.records.StackOverflowInfo;
import ru.tinkoff.edu.java.scrapper.webClients.dto.StackOverflowResponse;

public interface StackOverflowClient {
    StackOverflowResponse fetchInfo(StackOverflowInfo info);
}
