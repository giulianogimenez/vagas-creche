package br.edu.fatecsjc.creche.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.SitucaoInscricao;

import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long>{
	@Query("SELECT i FROM Inscricao i WHERE i.situacaoInscricao = 'LISTA_DE_ESPERA'")
	List<Inscricao>findAllInscricoesComListaDeEspera();
	
	@Query("SELECT i FROM Inscricao i WHERE i.situacaoInscricao = :situacao")
	List<Inscricao> findBySituacaoInscricao(@Param("situacao") SitucaoInscricao situacao);
}