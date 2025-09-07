create database inventario_bicicletas;
use inventario_bicicletas;

drop table if exists bicicletas;
create table bicicletas(
id int primary key auto_increment,
marca varchar(50) not null,
modelo varchar(5) not null,
color varchar(8) not null,
tipo varchar(20) not null,
precio double not null,
categoria varchar(10) not null,
stock int
);