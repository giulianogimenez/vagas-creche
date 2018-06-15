package br.edu.fatecsjc.creche.service;

import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class InstituicaoService {
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	//@PreAuthorize("hasRole('ADMIN')")
	public Instituicao criarInstituicao(String nome) {
		Instituicao i = new Instituicao();
		i.setNome(nome);
		i.setDataCadastro(LocalDateTime.now());
		return instituicaoRepository.save(i);
	}
	
}
