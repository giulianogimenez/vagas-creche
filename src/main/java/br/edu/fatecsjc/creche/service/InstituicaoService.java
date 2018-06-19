package br.edu.fatecsjc.creche.service;

import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstituicaoService {
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	@PreAuthorize("hasRole('ADMIN')")
	public Instituicao criarInstituicao(String nome) {
		Instituicao i = new Instituicao();
		i.setNome(nome);
		i.setDataCadastro(LocalDateTime.now());
		return instituicaoRepository.saveAndFlush(i);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Instituicao buscarPorId(Long id) {
		 return instituicaoRepository.findById(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Instituicao buscarPorNome(String nome) {
		return instituicaoRepository.findByNome(nome);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public List<Instituicao> listarTodos() {
		return instituicaoRepository.findAll();
	}
	
}
