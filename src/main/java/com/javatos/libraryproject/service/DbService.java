package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Author;
import com.javatos.libraryproject.model.Book;
import com.javatos.libraryproject.model.Genre;
import com.javatos.libraryproject.model.Publisher;
import com.javatos.libraryproject.repository.AuthorRepository;
import com.javatos.libraryproject.repository.BookRepository;
import com.javatos.libraryproject.repository.GenreRepository;
import com.javatos.libraryproject.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository  publisherRepository;

    @Autowired
    public DbService(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;

    }

    public void initDataBaseTest(){

        Author author1 = Author.builder().description("author1").name("paulo").nationality("Brazil").build();
        Author author2 = Author.builder().description("author2").name("pablo").nationality("Brazil").build();
        Author author3 = Author.builder().description("author3").name("peste").nationality("usa").build();

        var authors1 = Arrays.asList(author1,author3);
        var authors2 = List.of(author3);
        var authors3 = Arrays.asList(author2,author3,author1);

        Genre genre1 = new Genre(null,"terror");
        Genre genre2 = new Genre(null,"comedia");
        Genre genre3 = new Genre(null,"drama");

        var genres1 = Arrays.asList(genre1,genre3);
        var genres2 = Arrays.asList(genre3,genre2);
        var genres3 = List.of(genre2);

        Publisher publisher1 = new Publisher(null,"Globo");
        Publisher publisher2 = new Publisher(null,"MS books");

        Book book1 = new Book(null,"cronicas",authors1,450,genres1,2015,
                "BR00001TR","Cronicas de uma pessoa na puta que pariu",publisher2,"none");

        Book book2 = new Book(null,"molejos",authors2,40,genres3,2003,
                "BR0054985655TR","Molejos de uma pessoa na puta que pariu",publisher1,"none");

        Book book3 = new Book(null,"Peças",authors3,130,genres2,2021,
                "BR0435001TR","Peças de uma pessoa na puta que pariu",publisher2,"none");

        authorRepository.saveAll(Arrays.asList(author1,author2,author3));
        genreRepository.saveAll(Arrays.asList(genre1,genre2,genre3));
        publisherRepository.saveAll(Arrays.asList(publisher1,publisher2));
        bookRepository.saveAll(Arrays.asList(book1,book2,book3));

    }

}
