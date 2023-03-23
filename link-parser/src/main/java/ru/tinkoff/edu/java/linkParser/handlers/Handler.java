package ru.tinkoff.edu.java.linkParser.handlers;

import ru.tinkoff.edu.java.linkParser.records.LinkInfo;

public abstract class Handler {
    protected Handler successor;

    public abstract LinkInfo handleRequest(String link);

    public void setSuccessor(Handler successor) { this.successor = successor; }
}
