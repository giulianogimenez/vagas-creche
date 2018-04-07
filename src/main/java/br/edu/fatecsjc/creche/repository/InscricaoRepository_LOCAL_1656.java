package br.edu.fatecsjc.creche.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.fatecsjc.creche.model.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long>{
	@Query("SELECT i FROM Inscricao i WHERE i.situcaoInscricao = 'LISTA_DE_ESPERA'")
	List<Inscricao>findAllInscricoesComListaDeEspera();

}
