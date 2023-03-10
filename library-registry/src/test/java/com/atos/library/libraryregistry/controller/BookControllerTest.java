package com.atos.library.libraryregistry.controller;

import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.model.Book;
import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.model.Publisher;
import com.atos.library.libraryregistry.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookControllerTest {

    public static final String ID_AUTHOR = "63cd50ec40sdawe";

    public static final String ID_GENRE = "64Y342TTR";

    public static final String ID_PUBLISHER = "4Y35Y9HEHRWR8";

    public static final String ID_BOOK = "stre7trtewyrtew212";

    public static final String TESTID_INVALID = "testidInvalido";

    public static final String OBJECT_NOT_FOUND_BOOK = "Object not Found: " +
            TESTID_INVALID + " , type: " +
            Book.class.getName();

    public static final String DATA_VIOLATION_MESSAGE_BOOK = "Error creating :" +
            Book.class.getName();

    //Author
    public static final String DESCRIPTION = "ANTOINE JEAN-BAPTISTE MARIE ROGER FOSCOLOMBE, popularmente conhecido como Antonie de Saint-Exupéry foi escritor, ilustrador e piloto francês.";
    public static final String NATIONALITY = "França";
    public static final LocalDate BIRTH_DATE = LocalDate.of(1900, Month.JUNE, 29);
    public static final LocalDate REGISTER_DATE = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")));


    public static final String NAME_BOOK = "O pequeno Principe";
    public static final String NAME_AUTHOR = "Antonie de Saint-Exupéry";
    public static final String NAME_GENRE = "Romance";
    public static final String NAME_PUBLISHER = "Millenium";


    //BOOK

    public static final String SUMMARY = "Um avião pousado no deserto com o motor avariado, um piloto com uma pequena quantidade de água, " +
            "muito calor durante o dia e frio durante a noite";
    public static final String BOOK_COVER = "https://m.media-amazon.com/images/I/41yQ1HW1SwL.jpg";
    public static final String ASIN = "B0BPR1HQGX";
    public static final int PUBLICATION_YEAR = 1943;
    public static final int PAGES = 103;

    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
    @Mock
    private Book book;
    @Mock
    private Author author;
    @Mock
    private Genre genre;
    @Mock
    private Publisher publisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initModels();
    }

    @Test
    void whenFindByIdThenReturnOK() {
        when(bookService.findById(anyString())).thenReturn(book);
        ResponseEntity<Book> response = bookController.findById(ID_BOOK);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(book,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenFindAllThenReturnOK() {
        when(bookService.findAll()).thenReturn(List.of(book));
        ResponseEntity<List<Book>> response = bookController.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(List.of(book),response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(bookService.create(any())).thenReturn(book);
        ResponseEntity<Book> response = bookController.create(book);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(201,response.getStatusCodeValue());

    }

    @Test
    void whenUpdateThenReturnOK() {
        when(bookService.update(any())).thenReturn(book);

        ResponseEntity<Book> response = bookController.update(book);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenDeleteThenReturnNoContent() {
        doNothing().when(bookService).delete(any());

        ResponseEntity<Book> response = bookController.delete(ID_BOOK);

        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        verify(bookService,times(1)).delete(any());
        assertEquals(204,response.getStatusCodeValue());
    }
    void initModels() {
        author = Author.builder().id(ID_AUTHOR).description(DESCRIPTION).name(NAME_AUTHOR)
                .nationality(NATIONALITY).birthDate(BIRTH_DATE).registerDate(REGISTER_DATE)
                .build();
        genre = new Genre(ID_GENRE, NAME_GENRE);

        publisher = new Publisher(ID_PUBLISHER, NAME_PUBLISHER);

        book = new Book(ID_BOOK, NAME_BOOK,
                List.of(author),
                PAGES,
                List.of(genre),
                PUBLICATION_YEAR,
                ASIN,
                SUMMARY,
                publisher,
                BOOK_COVER);
    }
}