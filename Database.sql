create Database college;
use college;
create table students
(
	Name varchar(16),
    DOB varchar (10),
    Dept varchar(5),
    Batch int ,
    Email varchar (30) ,
    Password varchar(8),
    Primary Key(Email)
);

Create table canditates
(
	ID int Unique Auto_increment,
    Email varchar (30) ,
    Post varchar (20) Not Null,
    Vote int Default(0),
    Primary Key(Email)
);

create table voters
(
	No int Unique auto_increment,
	Email varchar (30) Unique
);

create table election
(
	Election varchar(5) default("true") Unique,
    Registration varchar(5) default("true")  Unique,
    Vote varchar(5) default("false")  Unique,
    Result varchar(5) default("false")  Unique
);

Insert into election values();

 

