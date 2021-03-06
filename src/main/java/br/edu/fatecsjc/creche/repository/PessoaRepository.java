package br.edu.fatecsjc.creche.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.edu.fatecsjc.creche.model.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Pessoa findById(Long id);
	List<Pessoa> findAll();
	Pessoa findOneByNome(String string);
	
	@Query("SELECT o.inscricao.pessoa FROM OpcaoInstituicao o WHERE o.instituicao.id = :id")
	List<Pessoa> findAllPessoasQueSeInscreveramNaInstituicao(Long id);
	
	@Query("SELECT i.pessoa FROM Inscricao i where i.id = ?1")
	Pessoa findOnePessoaPorInscricao(Long id);
	
    @Query("select o.inscricao.pessoa from OpcaoInstituicao o where o.instituicao.nome like %?1%")
    List<Pessoa> findPessoasByInscricaoInstituicao(String instituicaoNome);

    Pessoa findByNome(String nome);
}
