package com.example.tgbot.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Настройки Бота
 *
 * @author kimetsu - 25.11.2023 - 21:08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "bot")
public class BotProperties {
    private String token;
    private String username;
}
