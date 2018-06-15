package br.edu.fatecsjc.creche.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatecsjc.creche.model.OpcaoInstituicao;
import br.edu.fatecsjc.creche.repository.OpcaoInstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatecsjc.creche.dto.InscricaoDTO;
import br.edu.fatecsjc.creche.dto.OpcaoInstituicaoDTO;
import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Pessoa;
import br.edu.fatecsjc.creche.model.SitucaoInscricao;
import br.edu.fatecsjc.creche.repository.InscricaoRepository;
import br.edu.fatecsjc.creche.repository.InstituicaoRepository;

@Service
public class InscricaoService {
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private InscricaoRepository inscricaoRepository;
	@Autowired
    private OpcaoInstituicaoRepository opcaoInstituicaoRepository;
	@Autowired
    private InstituicaoRepository instituicaoRepository;
	
	@Transactional
	//@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Inscricao adicionarInscricao(String nome, LocalDate dataDeNascimento, List<OpcaoInstituicao> opcaoInstituicaoList) {
		Pessoa pessoa = pessoaService.criarPessoa(nome, dataDeNascimento);
		Inscricao inscricao = new Inscricao();
		inscricao.setDataCadastro(LocalDateTime.now());
		inscricao.setPessoa(pessoa);
		inscricao.setSitucaoInscricao(SitucaoInscricao.LISTA_DE_ESPERA);
		Inscricao i = inscricaoRepository.save(inscricao);
		List<OpcaoInstituicao> opcao1 = new ArrayList<>();
		opcaoInstituicaoList.forEach(o -> {
            o.setInscricao(inscricao);
            opcao1.add(opcaoInstituicaoRepository.save(o));
        });
	
		i.setOpcaoInstituicaoList(opcao1);
        i = inscricaoRepository.save(inscricao);
        return i;
    }
	
	@Transactional
	//@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public Inscricao adicionarInscricao(InscricaoDTO inscricaoDTO) {
		List<OpcaoInstituicao> opcaoInstituicaoList = new ArrayList<>();
		inscricaoDTO.getOpcoesInstituicao().forEach(o -> {
			OpcaoInstituicao opcaoInstituicao = new OpcaoInstituicao();
			opcaoInstituicao.setPosicao(o.getPosicao());
			opcaoInstituicao.setInstituicao(instituicaoRepository.findById(o.getInstituicaoId()));
			opcaoInstituicaoList.add(opcaoInstituicao);
		});
		return this.adicionarInscricao(inscricaoDTO.getNome(), inscricaoDTO.getDataNascimento(), opcaoInstituicaoList);
    }
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Inscricao buscarPorId(Long id) {
		return inscricaoRepository.findOne(id);
	}
}
