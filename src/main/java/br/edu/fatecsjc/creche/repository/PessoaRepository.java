package br.edu.fatecsjc.creche.repository;

import br.edu.fatecsjc.creche.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecsjc.creche.model.Pessoa;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select o.inscricao.pessoa from OpcaoInstituicao o where o.instituicao.nome like %?1%")
    List<Pessoa> findPessoasByInscricaoInstituicao(String instituicaoNome);
}
