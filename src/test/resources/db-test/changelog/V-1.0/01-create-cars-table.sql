create table cars (
       car_id bigint not null auto_increment,
        car_brand varchar(255),
        car_number varchar(255),
        model varchar(255),
        year integer,
        owner_id bigint,
        primary key (car_id)
    ) engine=InnoDB

GO

alter table cars
       add constraint FKm5ibu05fg8g81fo6491puswuu
       foreign key (owner_id)
       references users (id)