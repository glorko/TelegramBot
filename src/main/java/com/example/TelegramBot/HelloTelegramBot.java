
package com.example.TelegramBot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class HelloTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            // Check if the user wants to register
            if (messageText.equalsIgnoreCase("/register")) {
                sendRegistrationMessage(update.getMessage().getChatId());
            } else {
                sendWelcomeMessage(update.getMessage().getChatId());
            }
        }
    }

    private void sendWelcomeMessage(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Welcome to the Music Marketing Event Bot! 🎶\n\n"
                + "Are you ready to boost your music career? 🚀\n\n"
                + "Join our exclusive music marketing event and get insights from industry experts, network with fellow musicians, and take your music to the next level! 🎵✨\n\n"
                + "To register, simply type '/register' and let's get started! 📝🤝");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendRegistrationMessage(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Great choice! 🎉 To register for the Music Marketing Event, please visit our registration page at [Registration Link](https://www.example.com/registration) and complete the form. 📝✨\n\n"
                + "Once you're registered, you'll receive all the event details and updates. See you at the event! 🎵🤝");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "java_on_ai_bot";
    }

    @Override
    public String getBotToken() {
        return "6683148603:AAF9XzzaowZdMF_oZf4leF7kH6XDKdNVWOc";
    }
}

