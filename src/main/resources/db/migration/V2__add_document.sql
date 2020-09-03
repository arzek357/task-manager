create table documents (
  id                       bigserial,
  title                    varchar(255) not null,
  content                  varchar(1000) not null,
  comment                  varchar(255),
  primary key (id)
);

insert into documents (title, content, comment)
values
('Doc1', 'abc', 'comment1'),
('Doc2', 'qwerty', 'aaa'),
('Doc3','xyz', 'bbb');