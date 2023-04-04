package ru.tinkoff.edu.java.scrapper.dto.request;

import java.net.URL;

public record AddLinkRequest(URL link) implements LinkRequest {
    @Override
    public URL getLink() { return link; }
}
