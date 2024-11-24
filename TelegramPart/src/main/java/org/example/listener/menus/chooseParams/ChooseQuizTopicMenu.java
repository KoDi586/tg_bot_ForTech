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
public class ChooseQuizTopicMenu {

    private final TelegramBot telegramBot;
    private static final String TEXT_FOR_MAIN_MENU = "Настройка викторины:";

    public void sendMessage(long chatId) {

        InlineKeyboardButton button0 = new InlineKeyboardButton("музыка").callbackData("/musicCategory");
        InlineKeyboardButton button1 = new InlineKeyboardButton("фильмы и ТВ").callbackData("/filmAndTvCategory");
        InlineKeyboardButton button2 = new InlineKeyboardButton("История").callbackData("/historyCategory");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Еда и напитки").callbackData("/foodAndDrinkCategory");


        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{
                {button0, button1},
                {button2, button3}}
        );

        telegramBot.execute(
                new SendMessage(chatId,
                        TEXT_FOR_MAIN_MENU)
                        .replyMarkup(keyboard1));


    }

}
