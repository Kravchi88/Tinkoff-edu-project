package ru.tinkoff.edu.java.linkParser.records;

public record GitHubInfo(String userName, String repositoryName) implements LinkInfo {
    public String toString() {
        return userName + "/" + repositoryName;
    }
}
