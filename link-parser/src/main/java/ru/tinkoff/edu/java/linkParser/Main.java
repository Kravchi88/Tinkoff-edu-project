package ru.tinkoff.edu.java.linkParser;

import ru.tinkoff.edu.java.linkParser.handlers.GitHubHandler;
import ru.tinkoff.edu.java.linkParser.handlers.Handler;
import ru.tinkoff.edu.java.linkParser.handlers.StackOverflowHandler;
import ru.tinkoff.edu.java.linkParser.records.LinkInfo;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        /*String stringLink = "https://github.com/sanyarnd/tinkoff-java-course-2022/";
        URL link = new URL(stringLink);*/
    }

    public static LinkInfo parse(URL url){
        Handler handler1 = new GitHubHandler();
        Handler handler2 = new StackOverflowHandler();

        handler1.setSuccessor(handler2);
        return handler1.handleRequest(url);
    }
}
