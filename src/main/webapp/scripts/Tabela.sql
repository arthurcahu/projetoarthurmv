create database dbcafemv;
use dbcafemv;

create table Listadeparticipantes(
idcon int primary key auto_increment,
nome varchar (50) not null,
CPF varchar (11) not null,
contribuicao varchar (50) not null 
);

describe participantes;