package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BotController {
    private Map<Long, LinkUpdate> linkUpdateMap = new HashMap<>();
    @PostMapping("/update")
    public ResponseEntity<String> sendUpdate(@RequestBody LinkUpdate linkUpdate) {
        linkUpdateMap.put(linkUpdate.id(), linkUpdate);
        return ResponseEntity.ok("Update processed");
    }
}
