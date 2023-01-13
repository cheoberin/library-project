package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Book;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    /*
    public Book findById(long id){
        Optional<Book> book ; //TODO autorRepository.findById(id)
        return book.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        book.class.getName()));
    }

    public List<Book> findAll(){
        //TODO return autorRepository.findAll()
    }

    public Book create(Book book) {
        //TODO setId(null); return autorRepository.save();
    }

    public Book update (long id, Book bookNew){
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
        //TODO return autorRepository.save(autorOriginal);
    }

    public void delete(long id){
        findById(id);
        //TODO autorRepository.deleteById(id)
    }

    */

}
