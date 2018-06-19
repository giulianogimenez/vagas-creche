package br.edu.fatecsjc.creche.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatecsjc.creche.model.Encaminhamento;
import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.model.OpcaoInstituicao;
import br.edu.fatecsjc.creche.model.SitucaoInscricao;
import br.edu.fatecsjc.creche.repository.EncaminhamentoRepository;

@Service
public class EncaminhamentoService {
	@Autowired
    private EncaminhamentoRepository encaminhamentoRepository;
	@Autowired
	private OpcaoInstituicaoService opcaoInstituicaoService;
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Encaminhamento encaminhar(Long opcaoInstituicaoId) {
		OpcaoInstituicao opcaoInstituicao = opcaoInstituicaoService.buscarPorId(opcaoInstituicaoId);
		if (opcaoInstituicao == null) {
			return null;
		}
		Encaminhamento encaminhamento = new Encaminhamento();
		encaminhamento.setOpcaoInstituicao(opcaoInstituicao);
		encaminhamento.setDataEncaminhamento(LocalDate.now());
		encaminhamento.getOpcaoInstituicao().getInscricao().setSituacaoInscricao(SitucaoInscricao.ENCAMINHADA);
		return encaminhamentoRepository.saveAndFlush(encaminhamento);
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Encaminhamento> buscarTodos() {
		return this.encaminhamentoRepository.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Encaminhamento buscarPorId(Long id) {
		return encaminhamentoRepository.findOne(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Encaminhamento buscarPorInstituicao(Instituicao instituicao) {
		return encaminhamentoRepository.findByOpcaoInstituicaoInstituicao(instituicao);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Encaminhamento buscarPorInscricao(Inscricao inscricao) {
		return encaminhamentoRepository.findByOpcaoInstituicaoInscricao(inscricao);
	}
}
