package br.edu.uni7.tecnicas.controller;

import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.entities.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResponseEntity createCommit(){
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping ("api/commits")
    @ResponseBody
    public Map<Date, List<Commit>> listCommits() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        List<Commit> commits = new ArrayList<>();

        commits.add(new Commit("Atualização da parte do código",
                new Usuario("rochajpp"),
                "9d2a052407662e459e4a5423a76a2ff336b776e5",
                dateFormat.parse("11/09/2022 12:03:22")));

        commits.add(new Commit("Atualização da parte do código",
                new Usuario("rochajpp"),
                "9d2a052407662e459e4a5423a76a2ff336b776e5",
                dateFormat.parse("12/09/2022 01:15:23")));

        commits.add(new Commit("Atualização da parte do código",
                new Usuario("rochajpp"),
                "9d2a052407662e459e4a5423a76a2ff336b776e5",
                dateFormat.parse("11/09/2022 14:55:00")));

        commits.add(new Commit("Atualização da parte do código",
                new Usuario("rochajpp"),
                "9d2a052407662e459e4a5423a76a2ff336b776e5",
                dateFormat.parse("12/09/2022 00:00:00")));

        commits.add(new Commit("Atualização da parte do código",
                new Usuario("rochajpp"),
                "9d2a052407662e459e4a5423a76a2ff336b776e5",
                dateFormat.parse("10/09/2022 14:18:20")));

        commits.add(new Commit("Commit de teste sem fazer nada",
                new Usuario("jvaires"),
                "f22931bd4648094d34fe4ee986dba1553d0d1a77",
                dateFormat.parse("10/09/2022 12:00:00")));

        commits.add(new Commit("html",
                new Usuario("jvaires"),
                "8cd138b433be8cd1b98662b9a2a9707832d88703",
                dateFormat.parse("10/09/2022 12:00:00")));

        commits.add(new Commit("Initial commit",
                new Usuario("João"),
                "c019ed6dd75bd530a0adf0c7ee38abb92dcf5af7",
                dateFormat.parse("02/01/2022 12:00:00")));

        Map<Date, List<Commit>> commitsAgrupados = commits.stream().collect(Collectors.groupingBy(c -> c.getDiaData()));

        return commitsAgrupados;
    }

}
