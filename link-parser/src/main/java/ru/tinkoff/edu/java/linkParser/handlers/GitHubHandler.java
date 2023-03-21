package ru.tinkoff.edu.java.linkParser.handlers;

import ru.tinkoff.edu.java.linkParser.records.GitHubInfo;
import ru.tinkoff.edu.java.linkParser.records.LinkInfo;

public class GitHubHandler extends Handler{
    @Override
    public LinkInfo HandleRequest(String link) {

        if (!isGithubLink(link)) {
           if (getSuccessor() != null) { return getSuccessor().HandleRequest(link); }
           return null;
        }

        if (!isAppropriateLinkFormat(link)) { return null; }

        return new GitHubInfo(getUserName(link), getRepositoryName(link));
    }

    private static boolean isGithubLink(String link){
        return link.contains("github.com");
    }

    private static boolean isAppropriateLinkFormat(String link){
        String[] linkParts = link.split("/");
        return linkParts.length >= 5;
    }

    private static String getUserName(String link){
        String[] linkParts = link.split("/");
        return linkParts[3];
    }

    private static String getRepositoryName(String link){
        String[] linkParts = link.split("/");
        return linkParts[4];
    }
}
