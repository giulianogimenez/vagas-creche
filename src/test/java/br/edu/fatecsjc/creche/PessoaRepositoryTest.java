package br.edu.fatecsjc.creche;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatecsjc.creche.model.Pessoa;
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
}