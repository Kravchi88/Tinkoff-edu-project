package ru.tinkoff.edu.java.scrapper.controllerAPI;

import java.util.List;

public class ApiErrorResponse {
    protected String description;
    protected String code;
    protected String exceptionName;
    protected String exceptionMessage;
    protected List<String> stacktrace;
}
