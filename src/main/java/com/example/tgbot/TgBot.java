package com.example.tgbot;

import com.example.tgbot.configs.BotProperties;
import com.example.tgbot.domain.Message;
import com.example.tgbot.domain.User;
import com.example.tgbot.service.DailyDomainService;
import com.example.tgbot.service.MessageService;
import com.example.tgbot.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сервис с функционалом бота
 *
 * @author kimetsu - 25.11.2023 - 21:16
 */
@Component
public class TgBot extends TelegramLongPollingBot {

    private final BotProperties properties;
    private final UserService userService;
    private final DailyDomainService dailyDomainService;
    private final MessageService messageService;

    public TgBot(BotProperties properties,
                 UserService userService,
                 DailyDomainService dailyDomainService,
                 MessageService messageService) {
        super(properties.getToken());

        this.properties = properties;
        this.userService = userService;
        this.dailyDomainService = dailyDomainService;
        this.messageService = messageService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();

        if (update.getMessage().hasText()) {
            var message = update.getMessage().getText();
            //потом можно как-нибудь по адекватнее написать, но сейчас мне лень
            switch (message) {
                case "/start" -> {
                    var user = userService.create(User.builder()
                            .id(chatId)
                            .username(update.getMessage().getChat().getUserName())
                            .createdAt(LocalDateTime.now())
                            .lastMessage(LocalDateTime.now())
                            .build());
                    messageService.create(Message
                            .builder()
                            .message(update.getMessage().getText())
                            .user(user)
                            .createdAt(LocalDateTime.now())
                            .build());
                    sendBotMessage(chatId, """
                            Привет, %s
                            Я умею только записывать твои сообщения в БД.
                            Раз в сутки буду отправлять тебе сообщение о кол-во собраных доменов!
                            """.formatted(user.getUsername()));
                }
                default -> {
                    sendBotMessage(chatId, "Sosi xyi");
                    var user = userService.update(chatId);
                    messageService.create(Message
                            .builder()
                            .message(update.getMessage().getText())
                            .user(user)
                            .createdAt(LocalDateTime.now())
                            .build());
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    /**
     * Метод отправки сообщения от бота
     *
     * @param chatId  в какой чат должно прийти сообщение
     * @param message сообщение, которое должен отправить бот
     */
    private void sendBotMessage(Long chatId, String message) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Массовая рассылка
     */
    @Scheduled(cron = "0 0 12 * * *") // каждый день в 12 часов
    private void massSendMessage() {
        dailyDomainService.sendRequest();
        LocalDate now = LocalDate.now();
        var dailyDomains = dailyDomainService.getAll();
        var users = userService.getAll();
        users.forEach(u ->
                sendBotMessage(u.getId(),
                        "%s. Собрано %s доменов"
                                .formatted(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                        dailyDomains.size())
                )
        );
    }
}
