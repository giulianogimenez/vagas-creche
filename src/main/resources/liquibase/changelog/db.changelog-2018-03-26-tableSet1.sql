--liquibase formatted sql

--changeset giuliano:01 dbms:MySQL
CREATE TABLE pss_pessoa (
  pss_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  pss_nome VARCHAR(500) NOT NULL,
  pss_data_nascimento DATE NOT NULL,
  pss_data_cadastro DATETIME NOT NULL,
  PRIMARY KEY (pss_id)
);

CREATE TABLE ins_inscricao (
  ins_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  ins_data_cadastro DATETIME NOT NULL,
  ins_situacao INT NOT NULL,
  pss_id BIGINT(20) NOT NULL,
  PRIMARY KEY (ins_id),
  INDEX FK_INS_PSS_idx (pss_id ASC),
  CONSTRAINT FK_INS_PSS
    FOREIGN KEY (pss_id)
    REFERENCES pss_pessoa (pss_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE int_instituicao (
  int_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  int_nome VARCHAR(300) NOT NULL,
  int_data_cadastro DATETIME NOT NULL,
  PRIMARY KEY (int_id));
  
CREATE TABLE opi_opcao_instituicao (
  opi_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  int_id BIGINT(20) NOT NULL,
  ins_id BIGINT(20) NOT NULL,
  opi_posicao INT NOT NULL,
  PRIMARY KEY (opi_id),
  INDEX FK_OPI_INS_idx (ins_id ASC),
  INDEX FK_OPI_INT_idx (int_id ASC),
  CONSTRAINT FK_OPI_INS
    FOREIGN KEY (ins_id)
    REFERENCES ins_inscricao (ins_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_OPI_INT
    FOREIGN KEY (int_id)
    REFERENCES int_instituicao (int_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
