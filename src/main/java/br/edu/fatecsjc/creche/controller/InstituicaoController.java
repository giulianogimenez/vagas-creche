package br.edu.fatecsjc.creche.controller;

import br.edu.fatecsjc.creche.dto.InstituicaoDTO;
import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.repository.InstituicaoRepository;
import br.edu.fatecsjc.creche.service.InstituicaoService;
import br.edu.fatecsjc.creche.utils.Views;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/instituicao")
public class InstituicaoController {

    @Autowired
    InstituicaoRepository repository;
    @Autowired
    InstituicaoService instituicaoService;
    
    @ApiOperation(value = "Cadastra uma Instituição")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastrado com sucesso!") })
    @RequestMapping(value = "/salvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> cadastrarInstituicao(@RequestBody InstituicaoDTO instituicaoDto, UriComponentsBuilder ucBuilder) {
        Instituicao instituicao = instituicaoService.criarInstituicao(instituicaoDto.getNome());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/instituicoes/busca/id/{id}").buildAndExpand(instituicao.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Busca uma Instituição pelo id", response = Instituicao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
    @JsonView(Views.SemId.class)
    @RequestMapping(value = "/busca/id/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Instituicao buscaPorId(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @ApiOperation(value = "Busca uma Instituição pelo nome", response = Instituicao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
    @JsonView(Views.SemId.class)
    @RequestMapping(value = "/busca/nome/{nome}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Instituicao buscaPorNome(@PathVariable("nome") String nome) {
        return repository.findByNome(nome);
    }

    @ApiOperation(value = "Busca todas as Instituições", response = Instituicao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
    @JsonView(Views.Basico.class)
    @RequestMapping(value = "/busca/todos/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<Instituicao> listarTodos() {
        return repository.findAll();
    }
}
