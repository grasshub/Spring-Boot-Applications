CREATE TABLE employee (
	id BIGINT NOT NULL AUTO_INCREMENT,
	first_name varchar(255) not null,
	last_name varchar(255) not null, 
	primary key (id)
);

insert into employee (first_name, last_name) values ('John', 'Smith');