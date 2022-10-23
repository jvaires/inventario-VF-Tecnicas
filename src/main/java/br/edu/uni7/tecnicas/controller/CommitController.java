package br.edu.uni7.tecnicas.controller;

import br.edu.uni7.tecnicas.common.Sha256Generator;
import br.edu.uni7.tecnicas.dto.CommitDTO;
import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.entities.Usuario;
import br.edu.uni7.tecnicas.services.CommitService;
import br.edu.uni7.tecnicas.services.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CommitController {

    private final CommitService commitService;
    private final UsuarioService usuarioService;

    public CommitController(CommitService commitService, UsuarioService usuarioService) {
        this.commitService = commitService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("api/commits")
    @ResponseBody
    public ResponseEntity createCommit(@RequestBody CommitDTO commitDTO) throws JsonProcessingException {

        if(commitDTO != null)
        {
            String codigoCommit = Sha256Generator.sha256(commitDTO.getMensagem() + commitDTO.getAutor() + new Random().nextInt());

            Usuario usuario = new Usuario(commitDTO.getAutor());

            usuarioService.saveUsuario(usuario);

            Commit commit = new Commit(commitDTO.getMensagem(),
                    usuario,
                    codigoCommit,
                    new Date());

            commitService.saveCommit(commit);

            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/commits")
    @ResponseBody
    public ResponseEntity updateCommit(@RequestBody CommitDTO commitDTO)
    {
        if(commitDTO != null)
        {
            if(commitService.getCommitById(commitDTO.getCodigo()).isPresent())
            {
                Usuario usuario = usuarioService.saveUsuario(new Usuario(commitDTO.getAutor()));

                Commit commit = new Commit(commitDTO.getMensagem(), usuario, commitDTO.getCodigo(), new Date());

                commitService.saveCommit(commit);

                return new ResponseEntity(HttpStatus.CREATED);
            }

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("api/commits")
    @ResponseBody
    public ResponseEntity deleteCommit(@RequestBody CommitDTO commitDTO)
    {
        if(commitDTO != null && commitDTO.getCodigo() != null && commitDTO.getCodigo().trim() != "")
        {
            if(commitService.getCommitById(commitDTO.getCodigo()).isPresent())
            {
                commitService.deleteCommit(commitDTO.getCodigo());

                return new ResponseEntity(HttpStatus.OK);
            }

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/commits/{codigoCommit}")
    @ResponseBody
    public Commit getCommit(@PathVariable String codigoCommit)
    {
        Commit commitRetorno = null;

        if(codigoCommit != null && codigoCommit.trim() != "")
        {
            if(commitService.getCommitById(codigoCommit).isPresent())
            {
                commitRetorno = commitService.getCommitById(codigoCommit).get();
            }
        }

        return commitRetorno;
    }

    @GetMapping ("api/commits")
    @ResponseBody
    public Map<Date, List<Commit>> listCommits() {

        Map<Date, List<Commit>> commitsAgrupados = commitService.getCommits().stream().collect(Collectors.groupingBy(c -> c.getDiaData()));

        return commitsAgrupados;
    }

}
