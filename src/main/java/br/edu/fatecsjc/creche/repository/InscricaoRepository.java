package br.edu.fatecsjc.creche.repository;

import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

}
