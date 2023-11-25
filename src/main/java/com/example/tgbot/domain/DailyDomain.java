package com.example.tgbot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Модуль DailyDomain
 *
 * @author kimetsu - 25.11.2023 - 20:35
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "daily_domains")
public class DailyDomain {
    private static final String SEQ = "daily_domains_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(sequenceName = SEQ, name = SEQ, allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @JsonProperty(value = "domainname")
    @Column(name = "domainname")
    private String domainname;

    @JsonProperty(value = "hotness")
    @Column(name = "hotness")
    private Integer hotness;

    @JsonProperty(value = "price")
    @Column(name = "price")
    private Integer price;

    @JsonProperty(value = "x_value")
    @Column(name = "x_value")
    private Integer xValue;

    @JsonProperty(value = "yandex_tic")
    @Column(name = "yandex_tic")
    private Integer yandexTic;

    @JsonProperty(value = "links")
    @Column(name = "links")
    private Integer links;

    @JsonProperty(value = "visitors")
    @Column(name = "visitors")
    private Integer visitors;

    @JsonProperty(value = "registrar")
    @Column(name = "registrar")
    private String registrar;

    @JsonProperty(value = "old")
    @Column(name = "old")
    private Integer old;

    @JsonProperty(value = "delete_date")
    @Column(name = "delete_date")
    private Date deleteDate;

    @JsonProperty(value = "rkn")
    @Column(name = "rkn")
    private Boolean rkn;

    @JsonProperty(value = "judicial")
    @Column(name = "judicial")
    private Boolean judicial;

    @JsonProperty(value = "block")
    @Column(name = "block")
    private Boolean block;
}
