package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.MainMenu;
import org.springframework.stereotype.Component;

@Component("/start")
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final TelegramBot telegramBot;
    private final MainMenu mainMenu;
    public static String commandName = "/start";
    private static String HELLO = "! Рады приветствовать вас в самом лучшем боте для do quiz!" +
            " Здесь вы можете повикторинить на множество тематик! " +
            "Мой создатель очень рад тому что вы решили воспользоваться именно моими викторинами!!!" +


            " \nP.s. данный бот написан полностью на языке java тк его создаетель не захотел писать" +
            "меня с ипользованием js. данный бот берет викторины с сайта the-trivia-api.com/";

    @Override
    public void execute(Update update) {
        telegramBot.execute(new SendMessage(
                update.message().chat().id(),
                "Hello, " + update.message().chat().username() + HELLO
        ));

    mainMenu.sendMessage(update.message().chat().id());
    }
}
