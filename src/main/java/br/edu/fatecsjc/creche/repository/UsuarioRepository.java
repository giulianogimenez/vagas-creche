package br.edu.fatecsjc.creche.repository;

import br.edu.fatecsjc.creche.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
