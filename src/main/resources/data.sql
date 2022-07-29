insert into roles values (1, 'ROLE_ADMIN');
insert into roles values (2, 'ROLE_AGENT');
insert into roles values (3, 'ROLE_CUSTOMER');

insert into agency (address, code_agency, email, name, phone_number) values ('Online', '1', 'softIB@softIB.com', 'soft-ib', '12345678');

insert into users (username, email, password) values ('admin', 'admin@admin.com', 'password');
insert into user_roles values (1,1);
insert into agent (agent_id, birth_date, first_name, is_admin, last_name, personal_address, phone_number, id_agency) values ('11', '1991-02-06', 'agent1', true, 'first', 'fl dar', '15478963', '1');
update users set agent_id = 1 where id = 1;

insert into users (username, email, password) values ('agent', 'agent@agent.com', 'password');
insert into user_roles values (2,2);
insert into agent (agent_id, birth_date, first_name, is_admin, last_name, personal_address, phone_number, id_agency) values ('22', '1993-05-06', 'agent2', false, 'second', 'fl dar', '96315478', '1');
update users set agent_id = 2 where id = 2;

insert into account_request (birth_date, email, first_name, is_approved, job, last_name, phone_number, prefered_date, referrence, salary, state) values ('1996-08-06', 'customer@customer.com', 'customer1', true, 'RESIDENT', 'first', '54968723', null, false, '2000', 'Tunis');
insert into users (username, email, password) values ('customer', 'customer@customer.com', 'password');
insert into user_roles values (3,3);
insert into customer (birth_date, cin, first_name, job_status, last_name, personal_address, phone_number, salary, account_request_id, id_agency, sex) values ('1996-08-06', '03561485', 'customer1', 'RESIDENT', 'first', 'in home', '54968723', '2000', '1', '1', 'MALE');
insert into account(rib, account_number, balance, email, type, id_user) values ('1472583690','1','0','customer@customer.com','CURRENT_ACCOUNT','3')