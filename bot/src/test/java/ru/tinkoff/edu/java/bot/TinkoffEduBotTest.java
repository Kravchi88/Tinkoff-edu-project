package ru.tinkoff.edu.java.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@SpringBootTest
class TinkoffEduBotTest {
    @Autowired
    private TinkoffEduBot tinkoffEduBot;
    @Mock
    private Chat chat;
    @Mock
    private Message message;
    @Mock
    private Update update;
    private static final long chatId = 12345678;

    @BeforeEach
    void spying() {
        tinkoffEduBot = spy(tinkoffEduBot);
        tinkoffEduBot.bot = spy(tinkoffEduBot.bot);
        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(chatId);
    }

    @Test
    void returnEmptyListMessage() {

        final String text = "/list";
        when(message.text()).thenReturn(text);

        final String emptyListResponseMessage = "Links:\n" + "There is not links in your list";
        final ArgumentMatcher<BaseRequest> emptyListSendMessageTextMatcher =
                baseRequest -> {
                    if (baseRequest.getParameters() == null) {
                        return false;
                    }
                    if (!baseRequest.getParameters().containsKey("text")) {
                        return false;
                    }
                    return baseRequest.getParameters().get("text").equals(emptyListResponseMessage);
                };
        tinkoffEduBot.handleUpdate(update);

        assertAll(
                () -> verify(tinkoffEduBot).handleUpdate(update),
                () -> verify(tinkoffEduBot).handleList(chatId),
                () -> verify(tinkoffEduBot.bot).execute(any(SendMessage.class)),
                () -> verify(tinkoffEduBot.bot).execute(argThat(emptyListSendMessageTextMatcher))
        );
    }

    @Test
    void returnNotEmptyList(){

        final String text = "/list";
        when(message.text()).thenReturn(text);
        tinkoffEduBot.usersLinks.put(chatId, List.of("https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java",
                "https://github.com/Kravchi88/Tinkoff-edu-project"));

        final String notEmptyListResponseMessage = """
                Links:
                "`https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java`"
                "`https://github.com/Kravchi88/Tinkoff-edu-project`\"""";
        final ArgumentMatcher<BaseRequest> notEmptyListSendMessageTextMatcher =
                baseRequest -> {
                    if (baseRequest.getParameters() == null) {
                        return false;
                    }
                    if (!baseRequest.getParameters().containsKey("text")) {
                        return false;
                    }
                    return baseRequest.getParameters().get("text").equals(notEmptyListResponseMessage);
                };
        tinkoffEduBot.handleUpdate(update);

        assertAll(
                () -> verify(tinkoffEduBot).handleUpdate(update),
                () -> verify(tinkoffEduBot).handleList(chatId),
                () -> verify(tinkoffEduBot.bot).execute(any(SendMessage.class)),
                () -> verify(tinkoffEduBot.bot).execute(argThat(notEmptyListSendMessageTextMatcher))
        );
    }

    @Test
    void returnListOfOneLink(){

        final String text = "/list";
        when(message.text()).thenReturn(text);
        tinkoffEduBot.usersLinks.put(chatId, List.of("https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java"));

        final String notEmptyListResponseMessage = "Links:\n"+"\"`https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java`\"";
        final ArgumentMatcher<BaseRequest> notEmptyListSendMessageTextMatcher =
                baseRequest -> {
                    if (baseRequest.getParameters() == null) {
                        return false;
                    }
                    if (!baseRequest.getParameters().containsKey("text")) {
                        return false;
                    }
                    return baseRequest.getParameters().get("text").equals(notEmptyListResponseMessage);
                };
        tinkoffEduBot.handleUpdate(update);

        assertAll(
                () -> verify(tinkoffEduBot).handleUpdate(update),
                () -> verify(tinkoffEduBot).handleList(chatId),
                () -> verify(tinkoffEduBot.bot).execute(any(SendMessage.class)),
                () -> verify(tinkoffEduBot.bot).execute(argThat(notEmptyListSendMessageTextMatcher))
        );
    }
}