package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.dto.QuestionDto;
import org.example.listener.menus.QuizWorkingMenu;
import org.example.sevrice.UserService;
import org.springframework.stereotype.Component;

@Component("/startQuiz")
@RequiredArgsConstructor
public class StartQuizCommand implements Command {

    public static String commandName = "/startQuiz";
    private final TelegramBot telegramBot;
    private final UserService userService;
    private final QuizWorkingMenu quizWorkingMenu;


    @Override
    public void execute(Update update) {

        Long userChatId = update.callbackQuery().message().chat().id();
        String infoAboutStartMessage = "вы начали викторину";
        QuestionDto questionDto = userService.startQuiz(userChatId);

        telegramBot.execute(new SendMessage(
                userChatId,
                infoAboutStartMessage
        ));
//        QuestionResponseDto questionDto = userService.getContinueQuestion(userChatId);

        quizWorkingMenu.sendMessage(userChatId, questionDto);

    }
}
