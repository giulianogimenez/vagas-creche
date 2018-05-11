package br.edu.fatecsjc.creche.dto;

import lombok.Data;

public @Data class UsuarioDTO {
    private Long id;
    private String email;
    private String nome;
    private String senha;
}
