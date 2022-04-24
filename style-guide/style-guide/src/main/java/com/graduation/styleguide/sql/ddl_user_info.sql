create table user_info
(
    code     bigint auto_increment,
    email    varchar(50)  not null,
    password varchar(255) not null,
    auth     varchar(50)  null,
    constraint user_info_pk
        primary key (code)
);