package com.atos.library.libraryregistry.controller;


import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/author")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable String id) {
        Author author =  authorService.findById(id);
        return  ResponseEntity.ok().body(author);
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok().body(authors);
    }

    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<Author>> findByNationality(@PathVariable String nationality) {
        List<Author> authors = authorService.findByNationality(nationality);
        return ResponseEntity.ok().body(authors);
    }

    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody Author author) {
        log.info("iniciando o cadastro de um autor: {}", author);
        Author authorNew = authorService.create(author);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(authorNew.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Author> update(@Valid @RequestBody Author authorNew){
        Author author = authorService.update(authorNew);
        return ResponseEntity.ok().body(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable String id) {
        log.warn("excluindo um autor");
        authorService.delete(id);
        log.warn("autor excluido");
        return ResponseEntity.noContent().build();
    }

}
