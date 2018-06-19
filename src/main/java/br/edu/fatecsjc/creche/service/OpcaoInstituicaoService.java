package br.edu.fatecsjc.creche.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import br.edu.fatecsjc.creche.model.OpcaoInstituicao;
import br.edu.fatecsjc.creche.repository.OpcaoInstituicaoRepository;

@Service
public class OpcaoInstituicaoService {
	
	@Autowired
	OpcaoInstituicaoRepository opcaoInstituicaoRepository;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'SECRETARIA')")
	public OpcaoInstituicao buscarPorId(Long id) {
		return opcaoInstituicaoRepository.findOne(id);
	}
}
