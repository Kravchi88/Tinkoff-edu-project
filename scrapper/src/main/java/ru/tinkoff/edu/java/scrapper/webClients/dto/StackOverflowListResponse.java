package ru.tinkoff.edu.java.scrapper.webClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StackOverflowListResponse(
        @JsonProperty("items") List<StackOverflowResponse> items
) {}
