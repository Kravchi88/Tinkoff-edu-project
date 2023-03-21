package ru.tinkoff.edu.java.linkParser.records;

sealed public interface LinkInfo permits GitHubInfo, StackOverflowInfo {}
