create table furniture (
  id          bigserial,
  title       varchar(255) not null,
  type        varchar(255) not null,
  size        int not null,
  primary key (id)
);

insert into furniture (title, type, size)
values
('Adde', 'Chair', 1000),
('Linnmon', 'Table', 700),
('Neiden','Bed', 500);