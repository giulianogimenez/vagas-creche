--liquibase formatted sql

--changeset camilobmoreira:02 dbms:MySQL
CREATE TABLE usr_usuario (
  usr_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  usr_nome VARCHAR(500) NOT NULL,
  usr_email VARCHAR(500) NOT NULL,
  usr_password VARCHAR(500) NOT NULL,
  usr_data_cadastro DATETIME NOT NULL,
  PRIMARY KEY (usr_id)
);
