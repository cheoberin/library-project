package com.atos.library.libraryregistry.controller;


import com.atos.library.libraryregistry.model.Publisher;
import com.atos.library.libraryregistry.service.PublisherService;
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
@RequestMapping("/publisher")
@Slf4j
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> findById(@PathVariable String id) {
        Publisher publisher = publisherService.findById(id);
        return ResponseEntity.ok().body(publisher);
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> findAll() {
        List<Publisher> publishers = publisherService.findAll();
        return ResponseEntity.ok().body(publishers);
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@Valid @RequestBody Publisher publisher) {
        Publisher publisherNew = publisherService.create(publisher);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(publisherNew.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> delete(@PathVariable String id) {
        log.warn("Deleting an publisher");
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> update(@Valid @RequestBody Publisher publisher) {
        Publisher publisherNew =  publisherService.update(publisher);
        return ResponseEntity.ok().body(publisherNew);
    }
}
