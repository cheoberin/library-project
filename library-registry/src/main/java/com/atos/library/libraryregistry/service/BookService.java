package com.atos.library.libraryregistry.service;


import com.atos.library.libraryregistry.model.Book;
import com.atos.library.libraryregistry.repository.BookRepository;
import com.atos.library.libraryregistry.service.exceptions.DataViolationException;
import com.atos.library.libraryregistry.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(String id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        Book.class.getName()));
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book create(Book book) {
        try {
            return bookRepository.save(book);
        }catch(Exception ex){
            throw new DataViolationException("Error creating :" + Book.class.getName());
        }
    }

    public Book update (Book bookNew){
        Book bookOriginal = findById(bookNew.getId());
        bookOriginal.setName(bookNew.getName());
        bookOriginal.setSummary(bookNew.getSummary());
        bookOriginal.setBookCover(bookNew.getBookCover());
        bookOriginal.setPages(bookNew.getPages());
        bookOriginal.setAsin(bookNew.getAsin());
        bookOriginal.setPublisher(bookNew.getPublisher());
        bookOriginal.setAuthors(bookNew.getAuthors());
        bookOriginal.setGenres(bookNew.getGenres());
        bookOriginal.setPublicationYear(bookOriginal.getPublicationYear());

        return bookRepository.save(bookOriginal);
    }

    public void delete(String id){
        findById(id);
        bookRepository.deleteById(id);
    }

}
