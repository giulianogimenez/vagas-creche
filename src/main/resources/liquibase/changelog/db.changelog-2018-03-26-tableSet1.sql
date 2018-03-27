-- changeset 01: criação das tabelas.
CREATE TABLE pss_pessoa (
  pss_id BIGINT(20) NOT NULL,
  pss_nome VARCHAR(500) NOT NULL,
  pss_data_nascimento DATE NOT NULL,
  pss_data_cadastro DATETIME NOT NULL,
  PRIMARY KEY (pss_id)
);

