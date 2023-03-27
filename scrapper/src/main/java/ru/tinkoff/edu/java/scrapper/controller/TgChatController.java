package ru.tinkoff.edu.java.scrapper.controller;

import exceptions.ChatAlreadyExistsException;
import exceptions.ChatNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;
@RestController
@RequestMapping("/tg-chat")
public class TgChatController {
    private Set<Long> chats = new HashSet<>();
    @PostMapping("/{id}")
    public ResponseEntity<String> registerChat(@PathVariable long id) {
        if (chats.contains(id)) { throw new ChatAlreadyExistsException(); }
        chats.add(id);
        return ResponseEntity.ok("The chat is registered");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChat(@PathVariable long id) {
        if (!chats.contains(id)) { throw new ChatNotFoundException(); }
        chats.remove(id);
        return ResponseEntity.ok("Chat successfully deleted");
    }
}
