create table users (
       id bigint not null auto_increment,
        name varchar(255),
        surname varchar(255),
        primary key (id)
    ) engine=InnoDB