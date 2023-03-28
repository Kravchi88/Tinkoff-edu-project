package ru.tinkoff.edu.java.scrapper.webClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubResponse(
        @JsonProperty("id") long id,
        @JsonProperty("url") String url,
        @JsonProperty("name") String repoName,
        @JsonProperty("full_name") String fullName,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("updated_at") OffsetDateTime updatedAt,
        @JsonProperty("pushed_at") OffsetDateTime pushedAt
) {}
