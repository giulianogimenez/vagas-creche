package br.edu.fatecsjc.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecsjc.creche.model.Encaminhamento;
import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Instituicao;

public interface EncaminhamentoRepository extends JpaRepository<Encaminhamento, Long> {
	Encaminhamento findByOpcaoInstituicaoInstituicao(Instituicao instituicao);
	Encaminhamento findByOpcaoInstituicaoInscricao(Inscricao inscricao);
}
