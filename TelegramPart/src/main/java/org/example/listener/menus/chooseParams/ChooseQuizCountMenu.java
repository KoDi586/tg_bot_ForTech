package org.example.listener.menus.chooseParams;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChooseQuizCountMenu {

    private final TelegramBot telegramBot;
    private static final String TEXT_FOR_MAIN_MENU = "Настройка викторины:";

    public void sendMessage(long chatId) {

        InlineKeyboardButton button0 = new InlineKeyboardButton("5 шт").callbackData("/count5");
        InlineKeyboardButton button1 = new InlineKeyboardButton("10 шт").callbackData("/count10");
        InlineKeyboardButton button2 = new InlineKeyboardButton("20 шт").callbackData("/count20");

        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{
                {button0, button1},
                {button2}}
        );

        telegramBot.execute(
                new SendMessage(chatId,
                        TEXT_FOR_MAIN_MENU)
                        .replyMarkup(keyboard1));


    }

}
