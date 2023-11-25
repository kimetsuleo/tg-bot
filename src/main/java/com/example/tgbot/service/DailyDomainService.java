package com.example.tgbot.service;

import com.example.tgbot.domain.DailyDomain;
import com.example.tgbot.repositories.DailyDomainRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с {@link com.example.tgbot.domain.DailyDomain}
 *
 * @author kimetsu - 25.11.2023 - 21:33
 */
@Service
@RequiredArgsConstructor
public class DailyDomainService {
    private static final String URL = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";

    private final DailyDomainRepository repository;

    /**
     * Сенедер запроса к сервису
     */
    public void sendRequest() {
        try {
            repository.deleteAll();
            var restTemplate = new RestTemplate()
                    .getForObject(new URI(URL), String.class);

            var mapper = new ObjectMapper();
            List<DailyDomain> dailyDomains = mapper.readValue(restTemplate, new TypeReference<>() {});

            if (Objects.isNull(restTemplate)) {
                throw new IllegalStateException();
            }

            repository.saveAll(dailyDomains);
        } catch (URISyntaxException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DailyDomain> getAll() {
        return repository.findAll();
    }

}