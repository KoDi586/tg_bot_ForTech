package org.example.command.commandHeap.QuizTools.count;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.dto.UserRequestDto;
import org.example.listener.menus.QuizToolsMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component("/count10")
@RequiredArgsConstructor
public class Count10 implements Command {

    private final TelegramBot telegramBot;
    private final QuizToolsMenu toolsMenu;
    public static String commandName = "/count10";
    private final UserService userService;

    @Override
    public void execute(Update update) {
        Long userChatId = update.callbackQuery().message().chat().id();
        telegramBot.execute(new SendMessage(
                userChatId,
                "количество вопросов: 10"
        ));

        UserRequestDto user = userService.findUser(userChatId);
        user.setQuestionsCount(10);
        userService.saveUserInfo(user);

        toolsMenu.sendMessage(userChatId);
    }
}
