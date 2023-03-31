package com.example.boardproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String Hello (Model model) {
        model.addAttribute("username", "leedongkyun");
        return "greetings"; // templates/greetings.mustache 브라우저로 전송한다.
    }

    @GetMapping("/bye")
    public String SeeYouNext (Model model) {
        model.addAttribute("nickname", "leedongkyun");
    return  "goodbye";
    }
}
