create sequence settable_generator;
alter sequence settable_generator owner to postgres;

create table users
(
    id         uuid         not null
        primary key,
    city       varchar(255),
    country    varchar(255),
    house      varchar(255),
    street     varchar(255),
    created_at timestamp    not null,
    email      varchar(255) not null,
    is_vip     boolean default false,
    phone      varchar(255) not null,
    status     varchar(255),
    updated_at timestamp,
    username   varchar(255) not null
);

alter table users
    owner to postgres;



