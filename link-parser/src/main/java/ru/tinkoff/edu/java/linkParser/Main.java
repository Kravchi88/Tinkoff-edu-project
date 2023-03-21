package ru.tinkoff.edu.java.linkParser;

import ru.tinkoff.edu.java.linkParser.handlers.GitHubHandler;
import ru.tinkoff.edu.java.linkParser.handlers.Handler;
import ru.tinkoff.edu.java.linkParser.handlers.StackOverflowHandler;
import ru.tinkoff.edu.java.linkParser.records.LinkInfo;

public class Main {
    public static void main(String[] args) {

        String link = "https://stackoverflow.com/search?q=unsupported%20link";

        Handler handler1 = new GitHubHandler();
        Handler handler2 = new StackOverflowHandler();

        handler1.setSuccessor(handler2);
        LinkInfo result = handler1.HandleRequest(link);
        System.out.println(result);

    }
}
