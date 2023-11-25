package com.example.tgbot.service;

import com.example.tgbot.domain.Message;
import com.example.tgbot.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author kimetsu - 25.11.2023 - 23:10
 */
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;

    public Message create(Message message) {
        return repository.save(message);
    }
}
