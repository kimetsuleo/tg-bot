package com.example.tgbot.repositories;

import com.example.tgbot.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с {@link Message} в БД
 * @author kimetsu - 25.11.2023 - 21:15
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
