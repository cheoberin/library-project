package com.atos.library.libraryregistry.controller;


import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/genre")
@Slf4j
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findById(@PathVariable String id) {
        Genre genre = genreService.findById(id);
        return ResponseEntity.ok().body(genre);
    }

    @GetMapping
    public ResponseEntity<List<Genre>> findAll() {
        List<Genre> genres = genreService.findAll();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<Genre> create(@Valid @RequestBody Genre genre) {
        Genre genreNew = genreService.create(genre);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genreNew.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> delete(@PathVariable String id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> update(@Valid @RequestBody Genre genre) {
       Genre genreNew = genreService.update(genre);
       return ResponseEntity.ok().body(genreNew);
    }
}
