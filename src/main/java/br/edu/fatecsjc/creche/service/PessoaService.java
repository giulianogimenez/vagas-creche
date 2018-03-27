package br.edu.fatecsjc.creche.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.edu.fatecsjc.creche.model.Pessoa;

@Service
public class PessoaService {

	public Pessoa criarPessoa(String nome, LocalDate dataDeNascimento) {
		Pessoa p = new Pessoa();
		p.setNome(nome);
		p.setDataNascimento(dataDeNascimento);
		p.setDataCadastro(LocalDateTime.now());
		return p;
	}
	
}
