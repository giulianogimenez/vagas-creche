--liquibase formatted sql

--changeset camilobmoreira:03 dbms:MySQL
CREATE TABLE aut_autorizacao (
  aut_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  aut_nome VARCHAR(500) NOT NULL,
  PRIMARY KEY (aut_id)
) ENGINE=NDBCLUSTER;
