show databases;

create database if not exists employeemanagementsystem;

use employeemanagementsystem;

drop table if exists login;

create table login (
    username varchar(20),
    password varchar(20)
);

insert into login values ('admin', '12345');

select * from login;

desc login;