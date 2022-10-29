package br.edu.uni7.tecnicas.controller;

import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.entities.Usuario;
import br.edu.uni7.tecnicas.repository.CommitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CommitController {
    private CommitRepository repository;
//    List<Commit> commits = new ArrayList<>();
    @PostMapping("api/commits")
    @ResponseBody
    public ResponseEntity createCommit(@RequestBody Commit commit){
        if(commit != null) {
            commit.setData(new Date());
            while(true) {
                String code = (commit.generateCode());
                if(!repository.existsById(code)){
                    commit.setCodigo(code);
                    break;
                }
            }
            repository.save(commit);
//          commits.add(commit);
            return new ResponseEntity(HttpStatus.CREATED);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping( "api/commits")
    @ResponseBody
    public Iterable<Commit> listCommits(){
        return repository.findAll();
    }
//    public Map<Date, List<Commit>> listCommits() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Map<Date, List<Commit>> commitsAgrupados = commits.stream().collect(Collectors.groupingBy(c -> c.getDiaData()));
//        return commitsAgrupados;
//    }

}
