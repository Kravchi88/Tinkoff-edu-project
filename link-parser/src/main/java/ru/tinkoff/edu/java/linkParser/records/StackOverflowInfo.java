package ru.tinkoff.edu.java.linkParser.records;

public record StackOverflowInfo(int id) implements LinkInfo {
    public String toString() {
        return String.valueOf(id);
    }
}
