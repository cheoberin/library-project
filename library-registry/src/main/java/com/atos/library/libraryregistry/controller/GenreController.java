package com.atos.library.libraryregistry.controller;


import com.javatos.libraryproject.model.Genre;
import com.javatos.libraryproject.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/{id}")
    public Genre findById(@PathVariable String id) {
        return genreService.findById(id);
    }

    @GetMapping
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Genre create(@Valid @RequestBody Genre genre) {
        log.info("iniciando o cadastro de um gênero: {}", genre);
        return genreService.create(genre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.warn("excluindo um gênero");
        genreService.delete(id);
        log.warn("gênero excluido");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void Update(@PathVariable Genre genre) {
        genreService.update(genre);
        
    }
}
