create table products (
  id                       bigserial,
  title                    varchar(255) not null,
  description              varchar(255) not null,
  price                    int not null,
  primary key (id)
);

insert into products (title,description,price)
values
('Banana','Yellow fruit',50),
('Apple','Round fruit',40),
('Tomato','Red fruit',20);