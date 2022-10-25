package br.edu.uni7.tecnicas.controller;

import br.edu.uni7.tecnicas.common.Sha256Generator;
import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.repositories.ICommitRepository;
import br.edu.uni7.tecnicas.repositories.IUsuarioRepository;
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
    public ResponseEntity createCommit(@RequestBody Commit commit) {

        if(commit != null)
        {
            String codigoCommit = Sha256Generator.sha256(commit.getMensagem() + commit.getAutor() + new Random().nextInt());

            usuarioRepository.save(commit.getAutor());

            commit.setCodigo(codigoCommit);
            commit.setData(new Date());

            commitRepository.save(commit);

            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/commits")
    public ResponseEntity updateCommit(@RequestBody Commit commit)
    {
        if(commit != null)
        {
            if(commitRepository.existsById(commit.getCodigo()))
            {
                usuarioRepository.save(commit.getAutor());

                commit.setData(new Date());

                commitRepository.save(commit);

                return new ResponseEntity(HttpStatus.CREATED);
            }

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("api/commits")
    public ResponseEntity deleteCommit(@RequestBody Commit commit)
    {
        if(commit != null && commit.getCodigo() != null && commit.getCodigo().trim() != "")
        {
            if(commitRepository.existsById(commit.getCodigo()))
            {
                commitRepository.deleteById(commit.getCodigo());

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
