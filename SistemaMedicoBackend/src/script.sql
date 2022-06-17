create database sistemaMed;
use sistemaMed;

CREATE TABLE `User` (
	`idUser` INT NOT NULL,
	`password` varchar(255) NOT NULL,
	`roll` INT NOT NULL,
	PRIMARY KEY (`idUser`)
);

insert into user (idUser,password,roll) values (11,'1234',1);
insert into user (idUser,password,roll) values (22,'1234',2);
insert into user (idUser,password,roll) values (33,'1234',2);

CREATE TABLE `Medico` (
	`idUser` INT NOT NULL,
	`costo` FLOAT NOT NULL,
	`nombre` varchar(255) NOT NULL,
     estado int not null,
     decripcion varchar(255) not null,
     direccion varchar(255) not null,
     localidad varchar(255) not null,
     especialidad varchar(255) not null,
	`tiempoCita` INT NOT NULL
);

insert into Medico (idUser,costo,nombre,estado,decripcion,direccion,localidad,especialidad,tiempoCita) values(22,12560.2,'Rodrigo Alvarez',1,'nada','San Juan','San Pedro','General',20);

CREATE TABLE `Horario` (
	`medicoId` INT NOT NULL,
	`dia` varchar(255) NOT NULL,
	 apertura varchar(255) NOT NULL,
	 cierre varchar(255) NOT NULL
);

CREATE TABLE `Localidades` (
	`nombre` varchar(255) NOT NULL
);

CREATE TABLE `Especialidades` (
	`nombre` varchar(255) NOT NULL
);

CREATE TABLE `cita` (
`codigo` varchar(255) NOT NULL,
`estado` varchar(255) NOT NULL,
`paciente_Id` varchar(15) NOT NULL,
`idMedico` INT NOT NULL,
`fecha` varchar(255) NOT NULL,
`motivo` varchar(255) NOT NULL,
`tipo` varchar(255) NOT NULL,
 dia varchar(255) not null,
 hora varchar(255) not null,
`signos` varchar(255),
`diagnostico` varchar(255) ,
`prescMedicamentos` varchar(255),
PRIMARY KEY (`codigo`)
);
CREATE TABLE `paciente` (
`idPaciente` varchar(15) NOT NULL,
`infoContacto` varchar(255) NOT NULL,
`idMedico` INT NOT NULL,
`nombre` varchar(255) NOT NULL,
`edad` int NOT NULL,
PRIMARY KEY (`idPaciente`)
);

insert into paciente 


CREATE TABLE `Enfermedades` (
`id_paciente` varchar(15) NOT NULL,
`nombre` varchar(255) NOT NULL
);

CREATE TABLE `Expediente` (
`idExamen` varchar(255) NOT NULL,
`descripcion` varchar(255) NOT NULL,
`fecha` DATE NOT NULL,
`id_cita` varchar(255) NOT NULL,
`id_paciente` varchar(15) NOT NULL,
PRIMARY KEY (`idExamen`)
);


CREATE TABLE `cirugias` (
`id_paciente` varchar(15) NOT NULL,
`nombre` varchar(255) NOT NULL
);

CREATE TABLE `Alergias` (
`id_paciente` varchar(15) NOT NULL,
`nombre` varchar(255) NOT NULL
);