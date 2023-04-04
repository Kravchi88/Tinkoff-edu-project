package ru.tinkoff.edu.java.bot;

import com.pengrad.telegrambot.model.Update;

public interface UpdateHandler { void handleUpdate(Update update); }