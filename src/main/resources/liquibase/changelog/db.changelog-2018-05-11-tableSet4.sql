--liquibase formatted sql

--changeset camilobmoreira:03 dbms:MySQL
CREATE TABLE uau_usuario_autorizacao (
  usr_id BIGINT(20) NOT NULL,
  aut_id BIGINT(20) NOT NULL,
  CONSTRAINT FK_USR_USUARIO
    FOREIGN KEY (usr_id)
    REFERENCES usr_usuario (usr_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_AUT_AUTORIZACAO
    FOREIGN KEY (aut_id)
    REFERENCES aut_autorizacao (aut_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
