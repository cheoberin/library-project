package com.atos.library.libraryregistry.controller;


import com.atos.library.libraryregistry.service.BookService;
import com.atos.library.libraryregistry.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public Book findById(@PathVariable String id) {
        return bookService.findById(id);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book create(@Valid @RequestBody Book book) {
        log.info("iniciando o cadastro de um livro: {}", book);
        return bookService.create(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.warn("excluindo um livro");
        bookService.delete(id);
        log.warn("livro excluido");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void Update(@PathVariable Book newBook) {
        bookService.update(newBook);
        
    }
}
