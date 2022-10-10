package br.edu.uni7.tecnicas.controller;

import br.edu.uni7.tecnicas.common.Sha256Generator;
import br.edu.uni7.tecnicas.dto.CommitDTO;
import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.entities.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CommitController {

    Map<String, Commit> _commits = new HashMap<>();

    @PostMapping("api/commits")
    @ResponseBody
    public ResponseEntity createCommit(@RequestBody CommitDTO commitDTO) throws JsonProcessingException {

        if(commitDTO != null)
        {
            String codigoCommit = Sha256Generator.sha256(commitDTO.getMensagem() + commitDTO.getAutor() + new Random().nextInt());

            _commits.put(codigoCommit, new Commit(commitDTO.getMensagem(),
                    new Usuario(commitDTO.getAutor()),
                    codigoCommit,
                    new Date()));

            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/commits")
    @ResponseBody
    public ResponseEntity updateCommit(@PathVariable CommitDTO commitDTO)
    {
        if(commitDTO != null)
        {
            if(_commits.containsKey(commitDTO.getCodigo()))
            {
                Commit commit = _commits.get(commitDTO.getCodigo());

                commit.setMensagem(commitDTO.getMensagem());
                commit.setAutor(new Usuario(commitDTO.getAutor()));

                _commits.replace(commit.getCodigo(), commit);

                return new ResponseEntity(HttpStatus.CREATED);
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
            if(_commits.containsKey(codigoCommit))
            {
                commitRetorno = _commits.get(codigoCommit);
            }
        }

        return commitRetorno;
    }

    @GetMapping ("api/commits")
    @ResponseBody
    public Map<Date, List<Commit>> listCommits() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Map<Date, List<Commit>> commitsAgrupados = _commits.values().stream().collect(Collectors.groupingBy(c -> c.getDiaData()));

        return commitsAgrupados;
    }

}
