package org.example.command;

import com.pengrad.telegrambot.model.Update;
import org.example.command.commandHeap.*;
import org.example.command.commandHeap.QuizTools.topic.*;
import org.example.command.commandHeap.answers.FirstAnswer;
import org.example.command.commandHeap.answers.FourthAnswer;
import org.example.command.commandHeap.answers.SecondAnswer;
import org.example.command.commandHeap.answers.ThirdAnswer;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandContainer {

    private final ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<>();

    public CommandContainer(StartCommand startCommand,
                            StartQuizCommand startQuizCommand,
                            QuizEditorCommand quizEditorCommand,
                            BackInMainMenu backInMainMenu,
                            FirstAnswer firstAnswer,
                            SecondAnswer secondAnswer,
                            ThirdAnswer thirdAnswer,
                            FourthAnswer fourthAnswer,

                            ChooseQuizTopic chooseQuizTopic,
                            FilmAndTvCategory filmAndTvCategory,
                            FoodAndDrinkCategory foodAndDrinkCategory,
                            HistoryCategory historyCategory,
                            MusicCategory musicCategory) {


        commandMap.put(StartCommand.commandName, startCommand);
        commandMap.put(StartQuizCommand.commandName, startQuizCommand);
        commandMap.put(QuizEditorCommand.commandName, quizEditorCommand);
        commandMap.put(BackInMainMenu.commandName, backInMainMenu);
        commandMap.put(FirstAnswer.commandName, firstAnswer);
        commandMap.put(SecondAnswer.commandName, secondAnswer);
        commandMap.put(ThirdAnswer.commandName, thirdAnswer);
        commandMap.put(FourthAnswer.commandName, fourthAnswer);
        commandMap.put(ChooseQuizTopic.commandName, chooseQuizTopic);
        commandMap.put(FilmAndTvCategory.commandName, filmAndTvCategory);
        commandMap.put(FoodAndDrinkCategory.commandName, foodAndDrinkCategory);
        commandMap.put(HistoryCategory.commandName, historyCategory);
        commandMap.put(MusicCategory.commandName, musicCategory);
    }


    public void process(String commandName, Update update) {
        if (!commandMap.isEmpty()) {
            if (commandMap.containsKey(commandName)) {
                commandMap.get(commandName).execute(update);
            }
        }
    }
}
