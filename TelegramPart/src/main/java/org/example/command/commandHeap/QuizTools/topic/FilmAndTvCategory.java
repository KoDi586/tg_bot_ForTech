package org.example.command.commandHeap.QuizTools.topic;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.dto.UserRequestDto;
import org.example.listener.menus.MainMenu;
import org.example.listener.menus.QuizToolsMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component("/filmAndTvCategory")
@RequiredArgsConstructor
public class FilmAndTvCategory implements Command {

    private final TelegramBot telegramBot;
    private final QuizToolsMenu toolsMenu;
    public static String commandName = "/filmAndTvCategory";
    private final UserService userService;

    @Override
    public void execute(Update update) {
        Long userChatId = update.callbackQuery().message().chat().id();
        telegramBot.execute(new SendMessage(
                userChatId,
                "вы выбрали категорию фильмы и ТВ"
        ));
        UserRequestDto user = userService.findUser(userChatId);
        user.setQuizTopic("film_and_tv");
        userService.saveUserInfo(user);

        toolsMenu.sendMessage(userChatId);
    }
}
