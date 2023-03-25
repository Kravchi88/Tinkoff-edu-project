package ru.tinkoff.edu.java.linkParser.handlers;

import ru.tinkoff.edu.java.linkParser.records.GitHubInfo;
import ru.tinkoff.edu.java.linkParser.records.LinkInfo;
import java.net.URL;

public class GitHubHandler extends Handler {
    @Override
    public LinkInfo handleRequest(URL link) {

        if (!isGithubLink(link)) {
           if (successor != null) { return successor.handleRequest(link); }
           return null;
        }

        if (!isAppropriateLinkFormat(link)) { return null; }

        return new GitHubInfo(getUserName(link), getRepositoryName(link));
    }

    private static boolean isGithubLink(URL link) { return link.getHost().equals("github.com"); }

    private static boolean isAppropriateLinkFormat(URL link) {
        String[] linkParts = link.getFile().split("/");
        return linkParts.length >= 3;
    }

    private static String getUserName(URL link) {
        String[] linkParts = link.getFile().split("/");
        return linkParts[1];
    }

    private static String getRepositoryName(URL link) {
        String[] linkParts = link.getFile().split("/");
        return linkParts[2];
    }
}
