package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Book;
import com.atos.library.libraryregistry.repository.BookRepository;
import com.atos.library.libraryregistry.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book findById(String id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id + ", type: " + Book.class.getName()));
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book create(Book book){
        return bookRepository.save(book);
    }

    public Book update(Book bookNew){
        Book bookOld = findById(bookNew.getId());
        bookOld.setName(bookNew.getName());
        bookOld.setAuthors(bookNew.getAuthors());
        bookOld.setPages(bookNew.getPages());
        bookOld.setGenres(bookNew.getGenres());
        bookOld.setPublicationYear(bookNew.getPublicationYear());
        bookOld.setAsin(bookNew.getAsin());
        bookOld.setSummary(bookNew.getSummary());
        bookOld.setPublisher(bookNew.getPublisher());
        bookOld.setBookCover(bookNew.getBookCover());
        return bookRepository.save(bookOld);
    }

}
