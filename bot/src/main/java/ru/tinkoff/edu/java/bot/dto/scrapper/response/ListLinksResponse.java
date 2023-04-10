package ru.tinkoff.edu.java.bot.dto.scrapper.response;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ListLinksResponse(
        @NotNull List<LinkResponse> links,
        @NotNull Integer size
) {}