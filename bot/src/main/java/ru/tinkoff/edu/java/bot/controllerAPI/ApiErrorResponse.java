package ru.tinkoff.edu.java.bot.controllerAPI;

import java.util.List;

public class ApiErrorResponse {
    protected String description;
    protected String code;
    protected String exceptionName;
    protected String exceptionMessage;
    protected List<String> stacktrace;
}
