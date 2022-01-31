package com.example.biblio.controllers;

import com.example.biblio.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
/*
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "book-main";
    }

    @GetMapping("/book")
    public String book(Model model) {
        model.addAttribute("title", "Список книг");
        return "book-main";
    }
*/
    @GetMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("title", "Авторизация");
        return "auth";
    }


}