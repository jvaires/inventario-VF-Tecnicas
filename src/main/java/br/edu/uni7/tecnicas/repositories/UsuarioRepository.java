package br.edu.uni7.tecnicas.repositories;

import br.edu.uni7.tecnicas.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
}
