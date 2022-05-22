create table user_info
(
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
