insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email,name,surname)
values
('ferer1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com','Bob','Johnson'),
('kerer2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com','John','Johnson'),
('worker1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'worker1@gmail.com','John','Worker1');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);

insert into projects (name, description, creator_id) values ('name1', 'desc2', 1);

insert into users_projects(user_id, project_id) values (1, 1);

insert into taskstates (codename, name) values
('created', 'Создана'),
('in_progress', 'В работе'),
('on_review', 'Передана на проверку'),
('on_rework', 'Возвращена на доработку'),
('completed', 'Завершена'),
('canceled', 'Отменена');

insert into tasksauthorities (name) values ('creator'), ('performer'), ('subscriber');

insert into tasks (name, description, project_id, archived, deadline_time,state) values
('task1', 'desc1', 1, false, current_timestamp,'CREATED'),
('task2', 'desc2', 1, true, current_timestamp,'CREATED');
--
-- insert into tasksauthorities (name) values ('worker');
-- insert into taskparticipants (task_id, user_id, task_authority_id) values
-- (1, 1, 1), (1, 3, 1), (2, 3, 1);

-- insert into users_tasks(task_id, user_id) values
-- (1, 1), (1, 3), (2, 3);
