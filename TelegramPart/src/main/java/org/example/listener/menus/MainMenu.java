package org.example.listener.menus;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainMenu {

    private final TelegramBot telegramBot;
    private static final String TEXT_FOR_MAIN_MENU = "Настройка викторины:";

    public void sendMessage(long chatId) {

        InlineKeyboardButton button0 = new InlineKeyboardButton("Личный кабинет").callbackData("/LKab");
        InlineKeyboardButton button1 = new InlineKeyboardButton("Викторина").callbackData("/quizEditor");
        InlineKeyboardButton button2 = new InlineKeyboardButton("инфо").callbackData("/help");
        InlineKeyboardButton button3 = new InlineKeyboardButton("поддержать проект").callbackData("/donat");


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
