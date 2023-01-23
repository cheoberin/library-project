package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.model.Book;
import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.model.Publisher;
import com.atos.library.libraryregistry.repository.AuthorRepository;
import com.atos.library.libraryregistry.repository.BookRepository;
import com.atos.library.libraryregistry.repository.GenreRepository;
import com.atos.library.libraryregistry.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public DbService(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
    }

    public void initDataBaseTest() {

        Author author1 = Author.builder().description("author1").name("paulo").nationality("Brazilian").build();
        Author author2 = Author.builder().description("author2").name("pablo").nationality("Brazilian").build();
        Author author3 = Author.builder().description("author2").name("john").nationality("American").build();

        var authors1 = Arrays.asList(author1, author3);
        var authors2 = List.of(author3);
        var authors3 = Arrays.asList(author2, author3, author1);

        Genre genre1 = new Genre(null, "terror");
        Genre genre2 = new Genre(null, "comedia");
        Genre genre3 = new Genre(null, "drama");

        var genres1 = Arrays.asList(genre1, genre3);
        var genres2 = List.of(genre3, genre2);
        var genres3 = Arrays.asList(genre2);

        Publisher publisher1 = new Publisher(null, "Globo");
        Publisher publisher2 = new Publisher(null, "MS Books");

        Book book1 = new Book(null, "Cronicas", authors1, 450, genres1, 2015, "BR00001TR", "Cronicas de uma pessoa na puta que pariu", publisher1, "none");
        Book book2 = new Book(null, "Molejos", authors2, 50, genres2, 2002, "BR0054985655TR", "Molejos de uma pessoa na puta que pariu", publisher2, "none");
        Book book3 = new Book(null, "Peças", authors3, 352, genres3, 1995, "BR0435001TR", "Peças de uma pessoa na puta que pariu", publisher1, "none");

        authorRepository.saveAll(Arrays.asList(author1, author2, author2));
        genreRepository.saveAll(Arrays.asList(genre1, genre2, genre3));
        publisherRepository.saveAll(Arrays.asList(publisher1, publisher2));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));

    }

}
