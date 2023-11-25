package com.example.tgbot.repositories;

import com.example.tgbot.domain.DailyDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с {@link DailyDomain} в БД
 * @author kimetsu - 25.11.2023 - 21:15
 */
public interface DailyDomainRepository extends JpaRepository<DailyDomain, Long> {
}
