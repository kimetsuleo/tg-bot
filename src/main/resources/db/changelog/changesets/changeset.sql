-- liquibase formatted sql
--changeset l.frolenkov:TASK-1 context:master
--Комментарий: создание базовых таблиц
create table users
(
    id           serial primary key,
    username     character varying not null unique,
    created_at   timestamp without time zone default now(),
    last_message timestamp without time zone
);

create table messages
(
    id         serial primary key,
    message    character varying             not null,
    user_id    integer references users (id) not null,
    created_at timestamp without time zone
);

create table daily_domains
(
    id          serial primary key,
    domainname  character varying,
    hotness     int,
    price       int,
    x_value     int,
    yandex_tic  int,
    links       int,
    visitors    int,
    registrar   character varying,
    old         int,
    delete_date date,
    rkn         boolean,
    judicial    boolean,
    block       boolean
);

--rollback drop table users;
--rollback drop table messages;
--rollback drop table daily_domains;