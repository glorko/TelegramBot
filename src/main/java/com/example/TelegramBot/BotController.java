package com.example.TelegramBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Controller
public class BotController {

    private final BotService botService;

    @Autowired
    public BotController(BotService botService) {
        this.botService = botService;
    }

    public void processUpdate(TelegramLongPollingBot bot, Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            // Check if the user wants to register
            if (messageText.equalsIgnoreCase("/register")) {
                botService.sendRegistrationMessage(bot, update.getMessage().getChatId());
            } else if (messageText.contains("@")) {
                // Assume the message contains an email address (a basic check)
                botService.processRegistrationResponse(bot, update.getMessage().getChatId(), messageText);
            } else {
                botService.sendWelcomeMessage(bot, update.getMessage().getChatId());
            }
        }
    }
}