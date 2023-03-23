package ru.tinkoff.edu.java.linkParser.handlers;

import ru.tinkoff.edu.java.linkParser.records.LinkInfo;
import ru.tinkoff.edu.java.linkParser.records.StackOverflowInfo;

public class StackOverflowHandler extends Handler {

    @Override
    public LinkInfo handleRequest(String link) {

        if (!isStackOverflowLink(link)) {
            if (getSuccessor() != null) { return getSuccessor().handleRequest(link); }
            return null;
        }

        if (!isAppropriateLinkFormat(link)) { return null; }

        return new StackOverflowInfo(getQuestionId(link));
    }

    private static boolean isStackOverflowLink(String link){
        return link.contains("stackoverflow.com");
    }

    private static boolean isAppropriateLinkFormat(String link){
        String[] linkParts = link.split("/");
        if (linkParts.length < 5) { return false; }
        try {
            Integer.parseInt(linkParts[4]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int getQuestionId(String link){
        String[] linkParts = link.split("/");
        return Integer.parseInt(linkParts[4]);
    }
}
