package br.edu.fatecsjc.creche.repository;

import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    
}
