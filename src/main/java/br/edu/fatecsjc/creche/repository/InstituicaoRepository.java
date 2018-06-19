package br.edu.fatecsjc.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatecsjc.creche.model.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Instituicao findById(Long id);
    Instituicao findByNome(String nome);
}
