--liquibase formatted sql

--changeset giulianogimenez:05 dbms:MySQL
insert into usr_usuario values (2, 'Secretaria da Escola', 'secretaria@email.com', SHA1('12345'), current_date());
insert into aut_autorizacao values (2, 'ROLE_SECRETARIA');
insert into uau_usuario_autorizacao values (2,2);