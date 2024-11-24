package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.QuizToolsMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizEditorCommand implements Command {

    private final QuizToolsMenu toolsMenu;
    private final TelegramBot telegramBot;
    public static String commandName = "/quizEditor";
    private final UserService userService;

    @Override
    public void execute(Update update) {
        Long userChatId = update.callbackQuery().message().chat().id();
        telegramBot.execute(new SendMessage(
                userChatId,
                userService.getMessageForQuizCreate(userChatId)
        ));
        toolsMenu.sendMessage(userChatId);
    }

//    private String giveAllThemes() {
//        StringBuilder stringBuilder = new StringBuilder();
//        int item = 0;
//        for (String theme : quizService.getAllThemes()) {
//            stringBuilder.append("theme \"").append(theme).append("\" is number ").append(item).append("\n");
//            item++;
//        }
//        return stringBuilder.toString();
//    }
}
