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
public class QuizToolsMenu {
    private final TelegramBot telegramBot;
    private static final String TEXT_FOR_TOOlS_MENU = "Настройка викторины:";

    public void sendMessage(long chatId) {

        InlineKeyboardButton button0 = new InlineKeyboardButton("Начать викторину").callbackData("/startQuiz");
        InlineKeyboardButton button1 = new InlineKeyboardButton("Тема викторины").callbackData("/chooseQuizTopic");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Сложность").callbackData("/Difficulty");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Количество вопросов").callbackData("/chooseCountQuestion");
        InlineKeyboardButton button4 = new InlineKeyboardButton("На главную").callbackData("/backInMainMenu");



        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{
                {button0},
                {button1, button2},
                {button3, button4}}
        );

        telegramBot.execute(
                new SendMessage(chatId,
                        TEXT_FOR_TOOlS_MENU)
                        .replyMarkup(keyboard1));


    }

}
