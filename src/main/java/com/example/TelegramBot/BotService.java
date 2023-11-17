package com.example.TelegramBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotService {


    private final HelloTelegramBot telegramBot;

    @Autowired
    public BotService(HelloTelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendWelcomeMessage(TelegramLongPollingBot bot, Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message
                .setText("Welcome to the Music Marketing Event Bot! 🎶\n\n"
                        + "Are you ready to boost your music career? 🚀\n\n"
                        + "Join our exclusive music marketing event and get insights from industry experts, network with fellow musicians, and take your music to the next level! 🎵✨\n\n"
                        + "To register, simply type '/register' and let's get started! 📝🤝");

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendRegistrationMessage(TelegramLongPollingBot bot, Long chatId) {
        // Ask the user for their email
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Great choice! 🎉 To register for the Music Marketing Event, please provide your email address:");

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void processRegistrationResponse(TelegramLongPollingBot bot, Long chatId, String email) {
        // Save username and email to the database
        String username = "User"; // Replace with the actual username

        // Create a new User entity
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail(email);
//
//        // Save the user to the database
//        userRepository.save(user);

        // Send a confirmation message
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Thank you for registering with the email address: " + email + "\n\n"
                + "You are now registered for the Music Marketing Event! 🎵✨");

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Placeholder for saving user data to the database
    private void saveUserToDatabase(String username, String email) {
        // Implement database storage logic here
        // You can use a database library or framework for this purpose
        // For demonstration, this is a placeholder method
        System.out.println("Saving user data to the database: Username=" + username + ", Email=" + email);
    }


    // Use telegramBot to interact with the Telegram API
}