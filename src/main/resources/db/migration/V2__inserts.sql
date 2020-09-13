insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email,name,surname)
values
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com','Bob','Johnson'), --password: 100
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com','John','Johnson'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'worker1@gmail.com','John','Worker1'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'worker2@gmail.com','John','Worker2');

insert into users_roles (user_id, role_id)
values
(1, 1), (1, 2),
(2, 1),
(3, 1),
(4, 1);

insert into projects (name, description, creator_id) values ('name1', 'desc1', 1);

insert into users_projects(user_id, project_id) values (1, 1);

insert into tasks (name, description, project_id, archived, deadline_time,state) values
('task1', 'desc1', 1, false, current_timestamp,'CREATED'),
('task2', 'desc2', 1, true, current_timestamp,'CREATED');

insert into taskparticipants (user_id, task_id, authority) values
(1, 1, 'CREATOR'), (2, 1, 'PERFORMER'), (2, 2, 'CREATOR'), (2, 2, 'SUBSCRIBER')
--
-- insert into tasksauthorities (name) values ('worker');
-- insert into taskparticipants (task_id, user_id, task_authority_id) values
-- (1, 1, 1), (1, 3, 1), (2, 3, 1);

-- insert into users_tasks(task_id, user_id) values
-- (1, 1), (1, 3), (2, 3);
