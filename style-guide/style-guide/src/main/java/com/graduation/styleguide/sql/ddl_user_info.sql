create table user_info
(
<<<<<<< HEAD
    code     bigint auto_increment,
    email    varchar(50)  not null,
    password varchar(255) not null,
    auth     varchar(50)  null,
    constraint user_info_pk
        primary key (code)
);
=======
    code         bigint auto_increment
        primary key,
    email        varchar(50)  not null,
    password     varchar(255) not null,
    auth         varchar(50)  null,
    stylelist_id int(10)      null,
    `like`       varchar(255) null,
    constraint user_info_stylelist_id_uindex
        unique (stylelist_id),
    constraint FK_stylelist_id
        foreign key (stylelist_id) references subscribe (stylelist_id)
)
    comment '외래키 설정: ''user_info'' table의 stylelist_id는 ''subscribe'' table의 stylelist_id';
>>>>>>> e3f1920be13dbcfb142664a10a3bdb0c798d58c6
