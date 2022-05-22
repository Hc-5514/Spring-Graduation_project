create table clothes
(
    idx      bigint auto_increment
        primary key,
    id       varchar(50)  not null,
    title    varchar(50)  not null,
    pic_name varchar(255) not null,
    pic_path varchar(255) not null
);
