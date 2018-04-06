package br.edu.fatecsjc.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecsjc.creche.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Pessoa findOneByPssNome(String string);
	
}
