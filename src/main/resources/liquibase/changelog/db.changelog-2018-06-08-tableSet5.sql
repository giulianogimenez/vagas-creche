--liquibase formatted sql

--changeset giulianogimenez:05 dbms:MySQL
insert into usr_usuario values (1, 'Administrador', 'administrador@email.com', SHA1('admin'), current_date());
insert into aut_autorizacao values (1, 'ROLE_ADMIN');
insert into uau_usuario_autorizacao values (1,1);