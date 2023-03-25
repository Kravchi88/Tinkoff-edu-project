package ru.tinkoff.edu.java.linkParser.handlers;

import ru.tinkoff.edu.java.linkParser.records.LinkInfo;
import java.net.URL;

public abstract class Handler {
    protected Handler successor;

    public abstract LinkInfo handleRequest(URL link);

    public void setSuccessor(Handler successor) { this.successor = successor; }
}
