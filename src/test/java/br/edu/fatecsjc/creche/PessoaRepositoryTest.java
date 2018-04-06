package br.edu.fatecsjc.creche;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import br.edu.fatecsjc.creche.model.*;
import br.edu.fatecsjc.creche.repository.InscricaoRepository;
import br.edu.fatecsjc.creche.repository.InstituicaoRepository;
import br.edu.fatecsjc.creche.repository.OpcaoInstituicaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatecsjc.creche.repository.PessoaRepository;
import br.edu.fatecsjc.creche.service.PessoaService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaRepositoryTest {
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	InstituicaoRepository instituicaoRepository;

	@Autowired
	InscricaoRepository inscricaoRepository;

	@Autowired
	OpcaoInstituicaoRepository opcaoInstituicaoRepository;
	
	Pessoa  p;
	@Before
	public void setup() {
		p = new Pessoa();
	}
	
	@Test
	@Transactional
	public void adicionaPessoa() {
		p = pessoaService.criarPessoa("Camilo", LocalDate.of(1992, Month.OCTOBER, 23));
		p = pessoaRepository.save(p);
		Assert.assertNotNull(p.getId());
	}
	
	@Test
	@Transactional
	public void deletaPessoa() {
		p = pessoaService.criarPessoa("Camilo", LocalDate.of(1992, Month.OCTOBER, 23));
		p = pessoaRepository.save(p);
		pessoaRepository.delete(p);
		Assert.assertNull(pessoaRepository.findOne(p.getId()));
	}

	@Test
	@Transactional
	public void buscaPessoaPorInstituicao() {
		p = pessoaService.criarPessoa("Camilo", LocalDate.of(1992, Month.OCTOBER, 23));
		p = pessoaRepository.save(p);

		Instituicao instituicao = new Instituicao();
		instituicao.setNome("Escola 1");
		instituicao.setDataCadastro(LocalDateTime.now());
		instituicaoRepository.saveAndFlush(instituicao);

		Inscricao inscricao = new Inscricao();
		inscricao.setDataCadastro(LocalDateTime.now());
		inscricao.setPessoa(p);
		inscricao.setSitucaoInscricao(SitucaoInscricao.LISTA_DE_ESPERA);
		inscricaoRepository.saveAndFlush(inscricao);

		OpcaoInstituicao opcaoInstituicao = new OpcaoInstituicao();
		opcaoInstituicao.setPosicao(1);
		opcaoInstituicao.setInstituicao(instituicao);
		opcaoInstituicao.setInscricao(inscricao);
		opcaoInstituicaoRepository.saveAndFlush(opcaoInstituicao);

		Assert.assertTrue(pessoaRepository.findPessoasByInscricaoInstituicao("Escola").size() > 0);
	}
}
