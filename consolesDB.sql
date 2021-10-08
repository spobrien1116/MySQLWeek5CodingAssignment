create database if not exists consoles;

use consoles;

drop table if exists consoles;

create table consoles (
	name varchar(25) not null,
	controllers int(3) not null,
	release_year year not null,
	release_price double(8,2) not null,
	online_capable bool not null,
	primary key(name)
);