create database CarServices;
use CarService;

create table companies(
	id int primary key auto_increment,
	title varchar(45) not null unique,
    adress varchar(45) not null unique);
    
create table offices(
officeID int primary key auto_increment,
company__id int,
name varchar(63) not null,
type varchar(63) not null,
CONSTRAINT company__id FOREIGN KEY (company__id) REFERENCES companies (id));

create table services(
	serviceID int primary key auto_increment,
	serviceName varchar(45));
    
create table servicesPrice(
	office__ID INT(11) NOT NULL,
    service__ID INT(11) NOT NULL,
    PRIMARY KEY (office__ID, service__ID),
    INDEX office__ID (office__ID),
    INDEX service__ID (service__ID),
	CONSTRAINT FK_officeID FOREIGN KEY (office__ID) REFERENCES offices (officeID) ON DELETE CASCADE,
    CONSTRAINT FK_serviceID FOREIGN KEY (service__ID) REFERENCES services (serviceID) ON DELETE CASCADE,
    cost int default 20);

select * from servicesPrice;
select * from offices;
drop table offices;
drop table servicesPrice;

insert into companies value (null, 'Lukoil', 'Volgogradskaya str. 11');
insert into companies value (null, 'Gazprom', 'Moskovskaya str. 76');

insert into services values (null, 'Fuel'), (null, 'Carwash');
insert into services values (null, 'Fuel95'), (null, 'CarwashGold');

insert into offices values 
(null, 1, 'Lukoil01', 'Gas Station'), 
(null, 1, 'Lukoil02', 'Gas Station'), 
(null, 2, 'GazProm01', 'Car wash Station'),
(null, 2, 'GazProm02', 'Car wash Station');


insert into servicesPrice values 
(1, 1, 30),
(1, 3, 35),
(2, 2, 100),
(2, 4, 105),
(3, 1, 32),
(3, 3, 36),
(4, 2, 90),
(4, 4, 95);

CREATE USER 'root@localhost' IDENTIFIED BY 'password';
SET GLOBAL time_zone = '+3:00';
