package com.atos.library.libraryregistry.controller;


import com.javatos.libraryproject.model.Publisher;
import com.javatos.libraryproject.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
@Slf4j
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping("/{id}")
    public Publisher findById(@PathVariable String id) {
        return publisherService.findById(id);
    }

    @GetMapping
    public List<Publisher> findAll() {
        return publisherService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Publisher create(@Valid @RequestBody Publisher publisher) {
        log.info("iniciando o cadastro de um editor: {}", publisher);
        return publisherService.create(publisher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.warn("excluindo um editor");
        publisherService.delete(id);
        log.warn("editor excluido");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void Update(@PathVariable Publisher publisher) {
        publisherService.update(publisher);
        
    }
}
