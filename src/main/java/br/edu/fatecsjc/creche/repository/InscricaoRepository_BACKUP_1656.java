package br.edu.fatecsjc.creche.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.fatecsjc.creche.model.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long>{
	@Query("SELECT i FROM Inscricao i WHERE i.situcaoInscricao = 'LISTA_DE_ESPERA'")
	List<Inscricao>findAllInscricoesComListaDeEspera();
=======
import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
>>>>>>> c56e7157d72158f1768c51ba98fb1ca718dc1e5d

}
