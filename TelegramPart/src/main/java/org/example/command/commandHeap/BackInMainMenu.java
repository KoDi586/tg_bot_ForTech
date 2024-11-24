package org.example.command.commandHeap;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.command.Command;
import org.example.listener.menus.MainMenu;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BackInMainMenu implements Command {
    public static String commandName = "/backInMainMenu";
    private final MainMenu menu;

    @Override
    public void execute(Update update) {
        try {
            menu.sendMessage(update.callbackQuery().message().chat().id());
        } catch (Exception e) {
            log.warn("Ошибка в команде /backInMainMenu. Возможно кто-то хочет взаимодействовать через команды");
        }
    }
}
