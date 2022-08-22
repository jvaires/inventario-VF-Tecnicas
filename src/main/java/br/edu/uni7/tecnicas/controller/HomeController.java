package br.edu.uni7.tecnicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home2(){
        return "index2.html";
    }
//    @RequestMapping("/index2")
//    public String home() {
//        return "index.html";
//    }

}
