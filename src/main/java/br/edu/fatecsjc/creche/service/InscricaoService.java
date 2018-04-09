package br.edu.fatecsjc.creche.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.edu.fatecsjc.creche.model.OpcaoInstituicao;
import br.edu.fatecsjc.creche.repository.OpcaoInstituicaoRepository;
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
	@Autowired
    private OpcaoInstituicaoRepository opcaoInstituicaoRepository;
	
	@Transactional
	public Inscricao adicionarInscricao(String nome, LocalDate dataDeNascimento, List<OpcaoInstituicao> opcaoInstituicaoList) {
		Pessoa pessoa = pessoaService.criarPessoa(nome, dataDeNascimento);
		Inscricao inscricao = new Inscricao();
		inscricao.setDataCadastro(LocalDateTime.now());
		inscricao.setPessoa(pessoa);
		inscricao.setSitucaoInscricao(SitucaoInscricao.LISTA_DE_ESPERA);
		opcaoInstituicaoList.forEach(o -> {
            o.setInscricao(inscricao);
            opcaoInstituicaoRepository.save(o);
        });
        return inscricaoRepository.save(inscricao);
    }
}
