package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.model.Book;
import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.model.Publisher;
import com.atos.library.libraryregistry.repository.BookRepository;
import com.atos.library.libraryregistry.service.exceptions.DataViolationException;
import com.atos.library.libraryregistry.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTest {

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
    private BookService bookService;
    @Spy
    private BookRepository bookRepository;
    @Mock
    private Book book;
    @Mock
    private Author author;
    @Mock
    private Genre genre;
    @Mock
    private Publisher publisher;

    Optional<Book> bookOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initModels();
    }

    @Test
    void whenFindByIdThenReturnAnBookInstance() {
        when(bookRepository.findById(anyString())).thenReturn(bookOptional);

        Book response = bookService.findById(ID_BOOK);

        assertNotNull(response);
        assertEquals(Book.class,response.getClass());
        assertEquals(ID_BOOK,response.getId());
        assertEquals(NAME_BOOK,response.getName());
        assertEquals(SUMMARY,response.getSummary());
        assertEquals(PAGES,response.getPages());
        assertEquals(PUBLICATION_YEAR,response.getPublicationYear());
        assertEquals(BOOK_COVER,response.getBookCover());
        assertEquals(List.of(author),response.getAuthors());
        assertEquals(publisher,response.getPublisher());
        assertEquals(List.of(genre),response.getGenres());
        assertEquals(ASIN,response.getAsin());

    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){

        try{
            bookService.findById(TESTID_INVALID);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_BOOK,ex.getMessage());
        }


    }

    @Test
    void whenFindAllThenReturnAListOfBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> response = bookService.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Book.class,response.get(0).getClass());
        assertEquals(ID_BOOK,response.get(0).getId());

    }



    @Test
    void whenCreateThenReturnSuccess() {
        when(bookRepository.save(any())).thenReturn(book);

        Book response = bookService.create(book);

        assertNotNull(response);
        assertEquals(Book.class,response.getClass());
        assertEquals(ID_BOOK,response.getId());
        assertEquals(NAME_BOOK,response.getName());
        assertEquals(SUMMARY,response.getSummary());
        assertEquals(PAGES,response.getPages());
        assertEquals(PUBLICATION_YEAR,response.getPublicationYear());
        assertEquals(BOOK_COVER,response.getBookCover());
        assertEquals(List.of(author),response.getAuthors());
        assertEquals(publisher,response.getPublisher());
        assertEquals(List.of(genre),response.getGenres());
        assertEquals(ASIN,response.getAsin());
    }
    @Test
    void whenCreateThenReturnDataViolationException() {

        when(bookRepository.save(any()))
                .thenThrow(new DataViolationException(DATA_VIOLATION_MESSAGE_BOOK));

        try {
            bookService.create(book);
        }catch (Exception ex){
            assertEquals(DataViolationException.class,ex.getClass());
            assertEquals(DATA_VIOLATION_MESSAGE_BOOK,ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(bookRepository.findById(anyString())).thenReturn(bookOptional);
        when(bookRepository.save(any())).thenReturn(book);

        Book response = bookService.update(book);

        assertNotNull(response);
        assertEquals(Book.class,response.getClass());
        assertEquals(ID_BOOK,response.getId());
        assertEquals(NAME_BOOK,response.getName());
        assertEquals(SUMMARY,response.getSummary());
        assertEquals(PAGES,response.getPages());
        assertEquals(PUBLICATION_YEAR,response.getPublicationYear());
        assertEquals(BOOK_COVER,response.getBookCover());
        assertEquals(List.of(author),response.getAuthors());
        assertEquals(publisher,response.getPublisher());
        assertEquals(List.of(genre),response.getGenres());
        assertEquals(ASIN,response.getAsin());

    }

    @Test
    void whenUpdateThenReturnObjectNotFound() {
        when(bookRepository.findById(any()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND_BOOK));

        try{
            bookService.update(book);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_BOOK,ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(bookRepository.findById(anyString())).thenReturn(bookOptional);
        doNothing().when(bookRepository).delete(any());
        bookService.delete(ID_BOOK);
        verify(bookRepository,times(1)).deleteById(any());

    }

    @Test
    void deleteWithObjectNotFound() {
        when(bookRepository.findById(any())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND_BOOK));

        try{
            bookService.delete(ID_BOOK);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_BOOK,ex.getMessage());
        }
    }

    void initModels(){
        author = Author.builder().id(ID_AUTHOR).description(DESCRIPTION).name(NAME_AUTHOR)
                .nationality(NATIONALITY).birthDate(BIRTH_DATE).registerDate(REGISTER_DATE)
                .build();
        genre = new Genre(ID_GENRE, NAME_GENRE);

        publisher = new Publisher(ID_PUBLISHER,NAME_PUBLISHER);

        book = new Book(ID_BOOK, NAME_BOOK,
                List.of(author),
                PAGES,
                List.of(genre),
                PUBLICATION_YEAR,
                ASIN,
                SUMMARY,
                publisher,
                BOOK_COVER);

        bookOptional = Optional.of(new Book(ID_BOOK, NAME_BOOK,
                List.of(author),
                PAGES,
                List.of(genre),
                PUBLICATION_YEAR,
                ASIN,
                SUMMARY,
                publisher,
                BOOK_COVER));

    }
}