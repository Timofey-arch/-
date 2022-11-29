create database postgres owner postgres;
\CONNECT postgres

create table if not exists tv(
    id integer not null primary key,
    company varchar(255) not null,
    cost integer not null,
    diagonal integer not null,
    model varchar(255) not null,
    resolution varchar(255) not null,
    screen_type varchar(255) not null
);

create table if not exists users(
    id integer not null primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null
);

insert into tv values(1000, 'Samsung', 50, 25, 'RF-1050', '1920*1080', 'OLED') ,
(1001, 'Apple', 52, 40, 'Rg-10', '1920*100', 'ips'),
(1002, 'HP', 57, 45, 'ZH-2150', '1920*1080', 'TN+film');

-- ADMIN(Username - Admin, Password - 1234)
-- USER(Username - User, Password - 12345)
insert into users values (1, 'User', '$2a$12$3sxuvDMNcG7IXk3yrej6ketSfqV8orX5iR.OZ0pDpJqbh2cOLaJFW', 'ROLE_USER'),
                         (2, 'Admin', '$2a$12$Nj.Xy4GzFN7pfZbmZ/jf.e4AE7NGNgxRM/c7h2PeXYulxOHSlQFgG', 'ROLE_ADMIN');
