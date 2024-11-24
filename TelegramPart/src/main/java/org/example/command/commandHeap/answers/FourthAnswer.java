package org.example.command.commandHeap.answers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.dto.QuestionDto;
import org.example.listener.menus.QuizToolsMenu;
import org.example.listener.menus.QuizWorkingMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component("/fourthAnswer")
@RequiredArgsConstructor
public class FourthAnswer implements Command {
    public static String commandName = "/fourthAnswer";
    private final TelegramBot telegramBot;
    private final UserService userService;
    private final QuizWorkingMenu quizWorkingMenu;
    private final QuizToolsMenu toolsMenu;
    @Override
    public void execute(Update update) {
        Long userChatId = update.callbackQuery().message().chat().id();
        boolean isTrue = userService.checkAnswer(userChatId, 4);

        if (isTrue) {
            telegramBot.execute(new SendMessage(
                    userChatId,
                    "Верно!"

            ));
        } else {
            telegramBot.execute(new SendMessage(
                    userChatId,
                    "не верно."
            ));
        }

        try {
            QuestionDto questionDto = userService.getContinueQuestion(userChatId);
            quizWorkingMenu.sendMessage(userChatId, questionDto);
        } catch (Exception e) {
            telegramBot.execute(new SendMessage(
                    userChatId,
                    "викторина завершена! ваш результат: " + userService.findUser(userChatId).getTrueCount()
            ));

            toolsMenu.sendMessage(userChatId);
        }
    }
}
