package br.edu.fatecsjc.creche.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Pessoa;
import br.edu.fatecsjc.creche.model.SitucaoInscricao;
import br.edu.fatecsjc.creche.repository.InscricaoRepository;

@Service
public class InscricaoService {
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Transactional
	public Inscricao adicionarInscricao(String nome, LocalDate dataDeNascimento) {
		Pessoa pessoa = pessoaService.criarPessoa(nome, dataDeNascimento);
		Inscricao inscricao = new Inscricao();
		inscricao.setDataCadastro(LocalDateTime.now());
		inscricao.setPessoa(pessoa);
		inscricao.setSitucaoInscricao(SitucaoInscricao.LISTA_DE_ESPERA);
		return inscricaoRepository.save(inscricao);
	}
}
