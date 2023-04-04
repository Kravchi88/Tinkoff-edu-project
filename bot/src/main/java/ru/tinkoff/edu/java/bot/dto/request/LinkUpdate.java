package ru.tinkoff.edu.java.bot.dto.request;

import java.net.URL;
import java.util.List;

public record LinkUpdate(long id, URL url, String description, List<Long>tgChatIds) {}
