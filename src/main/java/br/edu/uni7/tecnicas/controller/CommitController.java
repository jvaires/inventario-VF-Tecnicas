package br.edu.uni7.tecnicas.controller;

import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.entities.Usuario;
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
    @PostMapping("api/commits")
    @ResponseBody
    public ResponseEntity createCommit(@RequestBody Commit commit){
            commit.setData(new Date());
            commit.getAutor();
            commit.setCodigo("213jo32ao");
            commits.add(commit);
            return new ResponseEntity(HttpStatus.CREATED);
    }
    List<Commit> commits = new ArrayList<>();

    @GetMapping ("api/commits")
    @ResponseBody

    public Map<Date, List<Commit>> listCommits() throws ParseException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Map<Date, List<Commit>> commitsAgrupados = commits.stream().collect(Collectors.groupingBy(c -> c.getDiaData()));

        return commitsAgrupados;
    }

}
