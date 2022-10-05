create database postgres owner postgres;
\CONNECT postgres

create table tv(
    id integer,
    company varchar(255) not null,
    cost integer not null,
    diagonal integer not null,
    model varchar(255) not null,
    resolution varchar(255) not null,
    screen_type varchar(255) not null
);

insert into tv values(1000, 'Samsung', 50, 25, 'RF-1050', '1920*1080', 'OLED') ,
(1001, 'Apple', 52, 40, 'Rg-10', '1920*100', 'ips'),
(1002, 'HP', 57, 45, 'ZH-2150', '1920*1080', 'TN+film');
