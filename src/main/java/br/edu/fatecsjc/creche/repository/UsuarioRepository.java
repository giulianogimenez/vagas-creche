package br.edu.fatecsjc.creche.repository;

import br.edu.fatecsjc.creche.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Usuario findById(Long id);
    Usuario findByNome(String nome);
}
