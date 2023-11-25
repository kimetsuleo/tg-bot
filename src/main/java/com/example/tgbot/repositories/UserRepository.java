package com.example.tgbot.repositories;

import com.example.tgbot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с {@link User} в БД
 *
 * @author kimetsu - 25.11.2023 - 20:39
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
