package br.edu.fatecsjc.creche;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatecsjc.creche.exception.UsuarioException;
import br.edu.fatecsjc.creche.model.Usuario;
import br.edu.fatecsjc.creche.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
	@Autowired
	private UsuarioService usuarioService;
	
	
	@Test(expected = UsuarioException.class)
	@Transactional
	public void emailVazioTest() {
		usuarioService.criarUsuario("", "Teste", "1234567");
	}
}
