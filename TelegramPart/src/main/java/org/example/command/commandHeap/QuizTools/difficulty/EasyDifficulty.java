package org.example.command.commandHeap.QuizTools.difficulty;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.dto.UserRequestDto;
import org.example.listener.menus.QuizToolsMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component("/easyDif")
@RequiredArgsConstructor
public class EasyDifficulty implements Command {

    private final TelegramBot telegramBot;
    private final QuizToolsMenu toolsMenu;
    public static String commandName = "/easyDif";
    private final UserService userService;

    @Override
    public void execute(Update update) {
        Long userChatId = update.callbackQuery().message().chat().id();
        telegramBot.execute(new SendMessage(
                userChatId,
                "вы выбрали сложность: ЛЕГКО"
        ));
        UserRequestDto user = userService.findUser(userChatId);
        user.setQuizDif("easy");
        userService.saveUserInfo(user);

        toolsMenu.sendMessage(userChatId);
    }
}