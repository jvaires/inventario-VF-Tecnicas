package br.edu.uni7.tecnicas.controller;

import br.edu.uni7.tecnicas.common.Sha256Generator;
import br.edu.uni7.tecnicas.dto.CommitDTO;
import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.entities.Usuario;
import br.edu.uni7.tecnicas.repositories.ICommitRepository;
import br.edu.uni7.tecnicas.repositories.IUsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CommitController {

    private final ICommitRepository commitRepository;
    private final IUsuarioRepository usuarioRepository;

    public CommitController(ICommitRepository commitRepository, IUsuarioRepository usuarioRepository) {
        this.commitRepository = commitRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("api/commits")
    public ResponseEntity createCommit(@RequestBody CommitDTO commitDTO) throws JsonProcessingException {

        if(commitDTO != null)
        {
            String codigoCommit = Sha256Generator.sha256(commitDTO.getMensagem() + commitDTO.getAutor() + new Random().nextInt());

            Usuario usuario = new Usuario(commitDTO.getAutor());

            usuarioRepository.save(usuario);

            Commit commit = new Commit(commitDTO.getMensagem(),
                    usuario,
                    codigoCommit,
                    new Date());

            commitRepository.save(commit);

            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/commits")
    public ResponseEntity updateCommit(@RequestBody CommitDTO commitDTO)
    {
        if(commitDTO != null)
        {
            if(commitRepository.existsById(commitDTO.getCodigo()))
            {
                Usuario usuario = usuarioRepository.save(new Usuario(commitDTO.getAutor()));

                Commit commit = new Commit(commitDTO.getMensagem(), usuario, commitDTO.getCodigo(), new Date());

                commitRepository.save(commit);

                return new ResponseEntity(HttpStatus.CREATED);
            }

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("api/commits")
    public ResponseEntity deleteCommit(@RequestBody CommitDTO commitDTO)
    {
        if(commitDTO != null && commitDTO.getCodigo() != null && commitDTO.getCodigo().trim() != "")
        {
            if(commitRepository.existsById(commitDTO.getCodigo()))
            {
                commitRepository.deleteById(commitDTO.getCodigo());

                return new ResponseEntity(HttpStatus.OK);
            }

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/commits/{codigoCommit}")
    public Commit getCommit(@PathVariable String codigoCommit)
    {
        Commit commitRetorno = null;

        if(codigoCommit != null && codigoCommit.trim() != "")
        {
            if(commitRepository.existsById(codigoCommit))
            {
                commitRetorno = commitRepository.findById(codigoCommit).get();
            }
        }

        return commitRetorno;
    }

    @GetMapping ("api/commits")
    public Map<Date, List<Commit>> listCommits() {

        Map<Date, List<Commit>> commitsAgrupados = commitRepository.findAll().stream().collect(Collectors.groupingBy(c -> c.getDiaData()));

        return commitsAgrupados;
    }

}
