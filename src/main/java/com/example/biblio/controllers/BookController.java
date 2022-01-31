package com.example.biblio.controllers;

import com.example.biblio.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.biblio.repo.BookRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.transaction.xa.XAException;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

import static java.lang.String.join;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value={"/", "/book-main"})
    public String book_Main(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book-main";

    }
/*
    @GetMapping("/book-main")
    public String bookMain(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book-main";

    }
*/
    @GetMapping("/book/add")
    public String bookAdd(Model model) {
        return "book-add";
    }

    @PostMapping("/book/add")
    public String bookDataAdd(@RequestParam String title, @RequestParam String author, @RequestParam String full_text, @RequestParam("file") MultipartFile file, Model model) throws IOException {

        byte[] cover = file.getBytes();

        Book book = new Book(title, full_text, author);
        book.setCover(cover);
        bookRepository.save(book);
        return "redirect:/book-main";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable(value = "id") long id, Model model) {
        if (!bookRepository.existsById(id)){
            return "redirect:/book-main";
        }
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        return "book-details";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEdit(@PathVariable(value = "id") long id, Model model) {
        if (!bookRepository.existsById(id)){
            return "redirect:/book-main";
        }
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        return "book-edit";
    }

    @PostMapping("/book/{id}/edit")
    public String bookDataUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String author, @RequestParam String full_text, Model model) {
        Book book = null;
        try {
            book = bookRepository.findById(id).orElseThrow(XAException::new);
        } catch (XAException e) {
            e.printStackTrace();
        }
        book.setTitle(title);
        book.setAuthor(author);
        book.setFull_text(full_text);
        bookRepository.save(book);
        return "redirect:/book-main";

    }

    @PostMapping("/book/{id}/remove")
    public String bookDataDelete(@PathVariable(value = "id") long id, Model model) {
        Book book = null;
        try {
            book = bookRepository.findById(id).orElseThrow(XAException::new);
        } catch (XAException e) {
            e.printStackTrace();
        }
        bookRepository.delete(book);
        return "redirect:/book-main";

    }
}