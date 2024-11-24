package org.example.command.commandHeap.QuizTools.count;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.dto.UserRequestDto;
import org.example.listener.menus.QuizToolsMenu;
import org.example.listener.menus.chooseParams.ChooseQuizCountMenu;
import org.example.listener.menus.chooseParams.ChooseQuizDifMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component("/chooseCountQuestion")
@RequiredArgsConstructor
public class ChooseCountQuestion implements Command {

    public static String commandName = "/chooseCountQuestion";
    private final TelegramBot telegramBot;

    private final ChooseQuizCountMenu chooseQuizDifMenu;


    @Override
    public void execute(Update update) {

        Long userChatId = update.callbackQuery().message().chat().id();
        String infoAboutStartMessage = "выберите количество вопросов:";

        telegramBot.execute(new SendMessage(
                userChatId,
                infoAboutStartMessage
        ));

        chooseQuizDifMenu.sendMessage(userChatId);

    }
}
