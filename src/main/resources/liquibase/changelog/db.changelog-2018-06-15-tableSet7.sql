--liquibase formatted sql

--changeset camilobmoreira:07 dbms:MySQL
CREATE TABLE enc_encaminhamento (
  enc_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  opi_id BIGINT(20) NOT NULL,
  enc_data_encaminhamento DATE NOT NULL,
  PRIMARY KEY (enc_id),
  CONSTRAINT FK_OPI_ENC
    FOREIGN KEY (opi_id)
    REFERENCES opi_opcao_instituicao (opi_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);