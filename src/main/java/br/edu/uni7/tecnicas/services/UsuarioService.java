package br.edu.uni7.tecnicas.services;

import br.edu.uni7.tecnicas.entities.Usuario;
import br.edu.uni7.tecnicas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> getUsuarioById(Integer usuarioId)
    {
        return usuarioRepository.findById(usuarioId);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario saveUsuario(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }
}
