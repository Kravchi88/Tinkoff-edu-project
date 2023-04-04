package ru.tinkoff.edu.java.bot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class TinkoffEduBot extends LongPollingBot{
    private static final String TYPE_NEW_LINK_CMD = "Enter the new link";
    private static final String REMOVE_LINK_CMD = "Enter the link which you want to remove";
    private static final String HELP_MESSAGE = """
                                /start -- register a user
                                /help -- display a window with commands
                                /track -- start tracking the link
                                /untrack -- stop tracking the link
                                /list -- show a list of tracked links""";

    Map<Long, ArrayList<String>> usersLinks = new HashMap<>();

    public TinkoffEduBot(ApplicationConfig applicationConfig) { super(applicationConfig.botConfig().token()); }

    @Override
    public void handleUpdate(Update update) {

        if (update.message() == null || update.message().text() == null) {
            System.out.println("Unknown update = " + update);
            return;
        }

        long chatId = update.message().chat().id();
        bot.execute(new SendChatAction(chatId, ChatAction.typing));
        switch (update.message().text()) {
            case "/start" -> handleStart(chatId);
            case "/help" -> handleHelp(chatId);
            case "/track" -> handleTrack(chatId);
            case "/untrack" -> handleUntrack(chatId);
            case "/list" -> handleList(chatId);
            default -> {
                if(update.message().replyToMessage() != null) { handleRepliedMessage(update, chatId); }
                else {
                    sendMessage(chatId, "command " + update.message().text() + " was not found");
                    sendMessage(chatId, HELP_MESSAGE);
                }
            }
        }

    }
    private void handleStart(long chatId) {
        usersLinks.put(chatId, new ArrayList<>());
        sendMessage(chatId, "You was successfully registered");
    }

    private void handleHelp(long chatId) {
        sendMessage(chatId, HELP_MESSAGE);
    }

    private void handleTrack(long chatId) {
        bot.execute(new SendMessage(chatId, TYPE_NEW_LINK_CMD).replyMarkup(new ForceReply(true)));
    }

    private void handleUntrack(long chatId) {
        bot.execute(new SendMessage(chatId, REMOVE_LINK_CMD).replyMarkup(new ForceReply(true)));
    }

    private void handleList(long chatId) {
        bot.execute(new SendMessage(chatId, "Your links:\n"
                + usersLinks.get(chatId).stream()
                .map(link1 -> "\"`" + link1 + "`\"")
                .reduce((link1, link2) -> link1 + "\n" + link2)
                .orElse("There is not links in your list"))
                .disableWebPagePreview(true)
                .parseMode(ParseMode.Markdown));
    }

    private void handleRepliedMessage(Update update, long chatId) {
        if (!usersLinks.containsKey(chatId)) {
            return;
        }
        switch (update.message().replyToMessage().text()) {
            case TYPE_NEW_LINK_CMD -> {
                if (usersLinks.get(chatId).contains(update.message().text())) {
                    sendMessage(chatId, "Link "+ update.message().text() + " the link is already being tracked");
                    return;
                }
                usersLinks.get(chatId).add(update.message().text());
                sendMessage(chatId, "Link " + update.message().text() + " was successfully added");
            }
            case REMOVE_LINK_CMD -> {
                if (!usersLinks.get(chatId).contains(update.message().text())) {
                    sendMessage(chatId, "Link \"" + update.message().text() + "\" was not found");
                    return;
                }
                usersLinks.get(chatId).remove(update.message().text());
                sendMessage(chatId, "Link " + update.message().text() + " was successfully removed");
            }
        }
    }
}
