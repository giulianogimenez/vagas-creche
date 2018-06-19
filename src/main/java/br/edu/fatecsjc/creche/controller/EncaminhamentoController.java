package br.edu.fatecsjc.creche.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.edu.fatecsjc.creche.model.Encaminhamento;
import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.model.OpcaoInstituicao;
import br.edu.fatecsjc.creche.repository.OpcaoInstituicaoRepository;
import br.edu.fatecsjc.creche.service.EncaminhamentoService;
import br.edu.fatecsjc.creche.service.OpcaoInstituicaoService;
import br.edu.fatecsjc.creche.utils.Views;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/encaminhamento")
public class EncaminhamentoController {
	@Autowired
	private EncaminhamentoService encaminhamentoService;
		
	@ApiOperation(value = "Encaminha uma inscricao")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastrado com sucesso!") })
	@RequestMapping(value = "/{opcaoInstituicaoId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> cadastrarEncaminhamento(@PathVariable("opcaoInstituicaoId") Long opcaoInstituicaoId, UriComponentsBuilder ucBuilder) {
		Encaminhamento encaminhamento = encaminhamentoService.encaminhar(opcaoInstituicaoId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/encaminhamento/{id}").buildAndExpand(encaminhamento.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	@ApiOperation(value = "Busca um Encaminhamento pelo id", response = Encaminhamento.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
	@JsonView(Views.SemId.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Encaminhamento> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Encaminhamento>(encaminhamentoService.buscarPorId(id), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Busca um Encaminhamento pela inscricao", response = Encaminhamento.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
	@JsonView(Views.SemId.class)
    @RequestMapping(value = "/inscricao/{inscricao}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Encaminhamento> buscarPorId(@PathVariable("inscricao") Inscricao inscricao) {
		return new ResponseEntity<Encaminhamento>(encaminhamentoService.buscarPorInscricao(inscricao), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Busca um Encaminhamento pela instituicao", response = Encaminhamento.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
	@JsonView(Views.SemId.class)
    @RequestMapping(value = "/instituicao/{instituicao}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Encaminhamento> buscarPorId(@PathVariable("instituicao") Instituicao instituicao) {
		return new ResponseEntity<Encaminhamento>(encaminhamentoService.buscarPorInstituicao(instituicao), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Busca todos os Encaminhamentos", response = Encaminhamento.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
	@JsonView(Views.SemId.class)
    @RequestMapping(value = "/todos/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Encaminhamento>> buscarListaDeEspera(@PathVariable("situacao") String situacao) {
		return new ResponseEntity<List<Encaminhamento>>(encaminhamentoService.buscarTodos(), HttpStatus.OK);
    }
}
