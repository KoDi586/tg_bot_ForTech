package org.example.listener.menus;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.dto.QuestionResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizWorkingMenu {

    private final TelegramBot telegramBot;

    public void sendMessage(long chatId, QuestionResponseDto questionDto) {

        String TEXT_FOR_MAIN_MENU = questionDto.getQuestionText();


        InlineKeyboardButton button0 = new InlineKeyboardButton(
                questionDto.getVariants().get(0)).callbackData("/firstAnswer");

        InlineKeyboardButton button1 = new InlineKeyboardButton(
                questionDto.getVariants().get(1)).callbackData("/secondAnswer");

        InlineKeyboardButton button2 = new InlineKeyboardButton(
                questionDto.getVariants().get(2)).callbackData("/thirdAnswer");

        InlineKeyboardButton button3 = new InlineKeyboardButton(
                questionDto.getVariants().get(3)).callbackData("/fourthAnswer");


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
