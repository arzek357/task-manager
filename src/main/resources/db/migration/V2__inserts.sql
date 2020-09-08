insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email,name,surname)
values
('admin', '$2y$10$OL9pKqHV/gQc3S.0Mu1.0OJix.faU8Jw8PghVhLpQgFGy1yS6U2Q2', 'bob_johnson@gmail.com','Bob','Johnson'), --passwd: admin
-- ('ferer1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com','Bob','Johnson'),
('kerer2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com','John','Johnson');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);

insert into projects (name, description, creator_id) values ('proj1', 'desc1', 1);