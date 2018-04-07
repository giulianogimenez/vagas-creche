package br.edu.fatecsjc.creche.repository;

import br.edu.fatecsjc.creche.model.OpcaoInstituicao;
import br.edu.fatecsjc.creche.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcaoInstituicaoRepository extends JpaRepository<OpcaoInstituicao, Long> {
    
}
