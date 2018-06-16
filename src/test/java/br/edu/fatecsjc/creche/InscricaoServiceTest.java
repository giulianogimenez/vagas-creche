package br.edu.fatecsjc.creche;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import br.edu.fatecsjc.creche.model.OpcaoInstituicao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.repository.InscricaoRepository;
import br.edu.fatecsjc.creche.service.InscricaoService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username="administrador@email.com",roles={"ADMIN"})
public class InscricaoServiceTest {
	@Autowired
	private InscricaoService inscricaoService;
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Test
	@Transactional
	public void testarListarPessoasInscritas() {
		inscricaoService.adicionarInscricao("Camilo", LocalDate.of(1992, Month.OCTOBER, 23), new ArrayList<OpcaoInstituicao>());
		Assert.assertFalse(inscricaoRepository.findAllInscricoesComListaDeEspera().isEmpty());
	}
}
