package br.edu.fatecsjc.creche;

import java.time.LocalDate;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.fatecsjc.creche.model.Pessoa;
import br.edu.fatecsjc.creche.service.PessoaService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaServiceTest {
	@Autowired
	PessoaService pessoaService;
	
	Pessoa  p;
	@Before
	public void estanciarPessoas() {
		p = new Pessoa();
	}
	
	@Test
	public void criarPessoa() {
		p = pessoaService.criarPessoa("Camilo", LocalDate.of(1992, Month.OCTOBER, 23));
		Assert.assertTrue(p.getNome().equals("Camilo"));
	}
}
