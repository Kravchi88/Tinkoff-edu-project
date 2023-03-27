package ru.tinkoff.edu.java.scrapper.controller;

import exceptions.ChatNotFoundException;
import exceptions.LinkAlreadyExistsException;
import exceptions.LinkNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinkResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/links")
public class LinksController {
    private Map<Long, ListLinkResponse> links = new HashMap<>();

    @GetMapping
    public ResponseEntity<String> getTrackedLinks(@RequestParam("Tg-Chat-Id") long tgChatId) {
        if (!links.containsKey(tgChatId)) { throw new ChatNotFoundException(); }
        return ResponseEntity.ok("Links successfully received");
    }

    @PostMapping
    public ResponseEntity<String> addLinkToTrack
            (@RequestParam("Tg-Chat-Id") long tgChatId, @RequestBody AddLinkRequest addLinkRequest) {
        LinkResponse link = convertRequestToResponse(tgChatId, addLinkRequest);
        if (links.get(tgChatId).links().contains(link)) { throw new LinkAlreadyExistsException(); }
        return ResponseEntity.ok("Link successfully added");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLinkFromTrack
            (@RequestParam("Tg-Chat-Id") long tgChatId, @RequestBody RemoveLinkRequest removeLinkRequest) {
        LinkResponse link = convertRequestToResponse(tgChatId, removeLinkRequest);
        if (!links.get(tgChatId).links().contains(link)) { throw new LinkNotFoundException(); }
        return ResponseEntity.ok("The link was successfully removed");
    }

    private LinkResponse convertRequestToResponse( long id, LinkRequest linkRequest) {
        return new LinkResponse(id, linkRequest.getLink());
    }
}