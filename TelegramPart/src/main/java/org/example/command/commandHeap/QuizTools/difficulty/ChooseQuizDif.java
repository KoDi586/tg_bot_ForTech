package org.example.command.commandHeap.QuizTools.difficulty;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.chooseParams.ChooseQuizDifMenu;
import org.example.listener.menus.chooseParams.ChooseQuizTopicMenu;
import org.springframework.stereotype.Component;

@Component("/chooseQuizDif")
@RequiredArgsConstructor
public class ChooseQuizDif implements Command {

    public static String commandName = "/chooseQuizDif";
    private final TelegramBot telegramBot;

    private final ChooseQuizDifMenu chooseQuizDifMenu;


    @Override
    public void execute(Update update) {

        Long userChatId = update.callbackQuery().message().chat().id();
        String infoAboutStartMessage = "выберите тему викторины из перечисленных";

        telegramBot.execute(new SendMessage(
                userChatId,
                infoAboutStartMessage
        ));

        chooseQuizDifMenu.sendMessage(userChatId);

    }
}
