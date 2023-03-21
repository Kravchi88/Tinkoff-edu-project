package ru.tinkoff.edu.java.linkParser.handlers;

import ru.tinkoff.edu.java.linkParser.records.LinkInfo;

public abstract class Handler {
    private Handler successor;

    public abstract LinkInfo HandleRequest(String link);

    public Handler getSuccessor() { return successor; }

    public void setSuccessor(Handler successor) { this.successor = successor; }
}
