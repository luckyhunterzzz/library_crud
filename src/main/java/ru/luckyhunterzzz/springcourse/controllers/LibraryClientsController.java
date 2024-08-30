package ru.luckyhunterzzz.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.luckyhunterzzz.springcourse.dao.LibraryClientDAO;
import ru.luckyhunterzzz.springcourse.models.LibraryClient;
import ru.luckyhunterzzz.springcourse.util.LibraryClientValidator;


@Controller
@RequestMapping("/library/clients")
public class LibraryClientsController {

    private final LibraryClientDAO libraryClientDAO;
    private final LibraryClientValidator libraryClientValidator;

    @Autowired
    public LibraryClientsController(LibraryClientDAO libraryClientDAO, LibraryClientValidator libraryClientValidator) {
        this.libraryClientDAO = libraryClientDAO;
        this.libraryClientValidator = libraryClientValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("libraryClients", libraryClientDAO.index());
        return "library/indexClient";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("libraryClient", libraryClientDAO.show(id));
        return "library/showClient";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("libraryClient", new LibraryClient());
        return "library/newClient";
    }

    @PostMapping()
    public String create(@ModelAttribute("libraryClient") @Valid LibraryClient libraryClient,
                         BindingResult bindingResult) {
        libraryClientValidator.validate(libraryClient, bindingResult);
        if (bindingResult.hasErrors()) {
            return "library/newClient";
        }
        libraryClientDAO.save(libraryClient);
        return "redirect:/library/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("libraryClient", libraryClientDAO.show(id));
        return "library/editClient";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("libraryClient") @Valid LibraryClient libraryClient,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        libraryClientValidator.validate(libraryClient, bindingResult);
        if (bindingResult.hasErrors()) {
            return "library/editClient";
        }
        libraryClientDAO.update(id, libraryClient);
        return "redirect:/library/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        libraryClientDAO.delete(id);
        return "redirect:/library/clients";
    }
}
