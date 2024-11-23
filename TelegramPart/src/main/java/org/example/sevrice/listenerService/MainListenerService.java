package org.example.sevrice.listenerService;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.command.CommandContainer;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainListenerService {

    private final TelegramBot telegramBot;
    private final CommandContainer commandContainer;
    private final String prefix = "/";

    public void dontUnderstand(Long userChatId) {
        telegramBot.execute(new SendMessage(
                userChatId,
                "I don't understand for U.\nPlease try write again."
        ));
    }

    public void workWithText(String text, Update update) {
        if (text.startsWith(prefix)) {
            commandContainer.process(text, update);
        } else {
            dontUnderstand(update.message().chat().id());
        }

    }

    public void workWithButton(Update update) {
        workWithText(update.callbackQuery().data(), update);
    }
}
