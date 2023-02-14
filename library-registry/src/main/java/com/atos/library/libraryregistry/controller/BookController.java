package com.atos.library.libraryregistry.controller;


import com.atos.library.libraryregistry.service.BookService;
import com.atos.library.libraryregistry.model.Book;
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
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private  BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable String id) {
        Book book =  bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        Book bookNew = bookService.create(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookNew.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@Valid @RequestBody Book newBook) {
        Book book = bookService.update(newBook);
        return ResponseEntity.ok().body(book);
    }
}
