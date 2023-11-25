package com.example.tgbot.service;

import com.example.tgbot.domain.User;
import com.example.tgbot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с Пользователем
 *
 * @author kimetsu - 25.11.2023 - 21:32
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(Long id) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastMessage(LocalDateTime.now());

        return repository.save(user);
    }

    public void delete(Long id) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        repository.delete(user);
    }
}
