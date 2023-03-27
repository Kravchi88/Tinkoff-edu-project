package ru.tinkoff.edu.java.scrapper.dto.request;

import java.net.URL;

sealed public interface LinkRequest permits AddLinkRequest, RemoveLinkRequest {
    URL getLink();
}
