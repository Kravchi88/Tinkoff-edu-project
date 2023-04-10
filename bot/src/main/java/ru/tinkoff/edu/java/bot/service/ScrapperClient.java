package ru.tinkoff.edu.java.bot.service;

import ru.tinkoff.edu.java.bot.dto.scrapper.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.ListLinksResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "localhost:8080")
public interface ScrapperClient {
    @PostExchange("/tg-chat/{id}")
    void registerUser(@PathVariable String id);
    @DeleteExchange("/tg-chat/{id}")
    void deleteUser(@PathVariable String id);
    @PostExchange("links")
    void addLink(@RequestBody AddLinkRequest addLinkRequest);
    @DeleteExchange("links")
    LinkResponse deleteLink(@RequestBody RemoveLinkRequest removeLinkRequest);
    @GetExchange("links")
    ListLinksResponse getLinks();
}
