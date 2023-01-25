package com.atos.library.libraryregistry.controller;


import com.javatos.libraryproject.model.Author;
import com.javatos.libraryproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public Author findById(@PathVariable String id) {
        return authorService.findById(id);
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/nationality/{nationality}")
    public Author findByNationality(@PathVariable Author author) {
        return authorService.findByNationality(author);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Author create(@Valid @RequestBody Author author) {
        log.info("iniciando o cadastro de um autor: {}", author);
        return authorService.create(author);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Author update (Author authorNew){
        return authorService.update(authorNew);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.warn("excluindo um autor");
        authorService.delete(id);
        log.warn("autor excluido");
    }

}
