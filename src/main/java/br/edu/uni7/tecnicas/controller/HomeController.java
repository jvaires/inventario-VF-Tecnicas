package br.edu.uni7.tecnicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView home() {
        var commit1 = List.of(new Commit("Inittial commit", "João",1234), new Commit("Update", "Paulo", 4321));
        ModelAndView commits = new ModelAndView("index.html");
        commits.addObject("commits", commit1);
        return commits;
    }

}
