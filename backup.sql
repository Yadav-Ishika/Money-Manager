create database saving;

use saving;

create table record (
    `User ID` varchar(6) not null,
    `Serial Number` int not null,
    `Transaction Number` int primary key,
    `Transaction Date` varchar(10) not null,
    `Transaction Time` varchar(8) not null,
    `Transaction Mode` varchar(6) not null,
    `Transaction Amount` bigint not null,
    `Transaction Reason` varchar(100)
);
select * from record;

create table `userdata` (
    `User ID` varchar(10) primary key,
    `User Password` varchar(100) not null,
    `User Aadhar Number` char(12) not null, 
    `User PAN Number` char(10) not null,
    `User Honorific` varchar(5) not null,
    `User FName` varchar(20) not null,
    `User MName` varchar(20) not null,
    `User LName` varchar(20) not null,
    `Current Evaluated Balance` bigint not null
);
select * from userdata;
