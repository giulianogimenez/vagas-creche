package br.edu.fatecsjc.creche.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import br.edu.fatecsjc.creche.model.Pessoa;
import br.edu.fatecsjc.creche.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Pessoa criarPessoa(String nome, LocalDate dataDeNascimento) {
		Pessoa p = new Pessoa();
		p.setNome(nome);
		p.setDataNascimento(dataDeNascimento);
		p.setDataCadastro(LocalDateTime.now());
		return pessoaRepository.save(p);
	}
	
}
