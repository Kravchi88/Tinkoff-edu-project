package ru.tinkoff.edu.java.linkParser.handlers;

import ru.tinkoff.edu.java.linkParser.records.LinkInfo;
import ru.tinkoff.edu.java.linkParser.records.StackOverflowInfo;
import java.net.URL;

public class StackOverflowHandler extends Handler {
    @Override
    public LinkInfo handleRequest(URL link) {

        if (!isStackOverflowLink(link)) {
            if (successor != null) { return successor.handleRequest(link); }
            return null;
        }

        if (!isAppropriateLinkFormat(link)) { return null; }

        return new StackOverflowInfo(getQuestionId(link));
    }

    private static boolean isStackOverflowLink(URL link){
        return link.getHost().equals("stackoverflow.com");
    }

    private static boolean isAppropriateLinkFormat(URL link) {
        String[] linkParts = link.getFile().split("/");
        if (linkParts.length < 3) { return false; }
        try {
            Integer.parseInt(linkParts[2]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static long getQuestionId(URL link) {
        String[] linkParts = link.getFile().split("/");
        return Long.parseLong(linkParts[2]);
    }
}
