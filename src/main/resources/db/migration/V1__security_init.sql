create table users (
    id                    bigserial,
    username              varchar(30) not null,
    password              varchar(80) not null,
    email                 varchar(50) unique,
    name                  varchar(255) not null,
    surname               varchar(255) not null,
    created_at            timestamp default current_timestamp,
    updated_at            timestamp default current_timestamp,
    primary key (id)
);

create table roles (
    id                    serial,
    name                  varchar(50) not null,
    created_at            timestamp default current_timestamp,
    updated_at            timestamp default current_timestamp,
    primary key (id)
);

CREATE TABLE users_roles (
    user_id               bigint not null,
    role_id               int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email,name,surname)
values
('ferer1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com','Bob','Johnson'),
('kerer2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com','John','Johnson');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);