package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Book;
import com.javatos.libraryproject.repository.BookRepository;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
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
         return bookRepository.save(book);
    }

    public Book update (String id, Book bookNew){
        Book bookOriginal = findById(id);
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
