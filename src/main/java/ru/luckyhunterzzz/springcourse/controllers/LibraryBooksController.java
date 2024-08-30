package ru.luckyhunterzzz.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.luckyhunterzzz.springcourse.dao.LibraryBookDAO;
import ru.luckyhunterzzz.springcourse.dao.LibraryClientDAO;
import ru.luckyhunterzzz.springcourse.models.LibraryBook;
import ru.luckyhunterzzz.springcourse.models.LibraryClient;

import java.util.List;


@Controller
@RequestMapping("/library/books")
public class LibraryBooksController {
    private final LibraryBookDAO libraryBookDAO;
    @Autowired
    private LibraryClientDAO libraryClientDAO;

    @Autowired
    public LibraryBooksController(LibraryBookDAO libraryBookDAO) {
        this.libraryBookDAO = libraryBookDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("libraryBooks", libraryBookDAO.index());
        return "library/indexBook";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("libraryBook", libraryBookDAO.show(id));
        model.addAttribute("libraryClients", libraryClientDAO.index());
        return "library/showBook";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("libraryBook", new LibraryBook());
        return "library/newBook";
    }

    @PostMapping()
    public String create(@ModelAttribute("libraryBook") @Valid LibraryBook libraryBook,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "library/newBook";
        }
        libraryBookDAO.save(libraryBook);
        return "redirect:/library/books";
    }

    @PostMapping("/addClient")
    public String addClientToBook(@RequestParam("bookId") int bookId,
                                  @RequestParam("clientId") int clientId) {
        libraryBookDAO.assignClient(bookId, clientId);
        return "redirect:/library/books/" + bookId;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("libraryBook", libraryBookDAO.show(id));
        return "library/editBook";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("libraryBook") @Valid LibraryBook libraryBook,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "library/editBook";
        }
        libraryBookDAO.update(id, libraryBook);
        return "redirect:/library/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int id) {
        libraryBookDAO.deleteClient(id);
        return "redirect:/library/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        libraryBookDAO.delete(id);
        return "redirect:/library/books";
    }
}
