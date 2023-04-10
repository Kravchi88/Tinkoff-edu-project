package ru.tinkoff.edu.java.bot.dto.scrapper.request;

import jakarta.validation.constraints.NotNull;

public record RemoveLinkRequest(@NotNull String link) {}