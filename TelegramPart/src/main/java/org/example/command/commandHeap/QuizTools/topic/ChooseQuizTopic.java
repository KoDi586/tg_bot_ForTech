package org.example.command.commandHeap.QuizTools.topic;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.chooseParams.ChooseQuizTopicMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component("/chooseQuizTopic")
@RequiredArgsConstructor
public class ChooseQuizTopic implements Command {

    public static String commandName = "/chooseQuizTopic";
    private final TelegramBot telegramBot;

    private final ChooseQuizTopicMenu chooseQuizTopicMenu;


    @Override
    public void execute(Update update) {

        Long userChatId = update.callbackQuery().message().chat().id();
        String infoAboutStartMessage = "выберите тему викторины из перечисленных";

        telegramBot.execute(new SendMessage(
                userChatId,
                infoAboutStartMessage
        ));

        chooseQuizTopicMenu.sendMessage(userChatId);

    }
}
