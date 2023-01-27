package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.repository.AuthorRepository;
import com.atos.library.libraryregistry.resources.exceptions.DataViolationException;
import com.atos.library.libraryregistry.resources.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


@SpringBootTest
class AuthorServiceTest {

    public static final String ID = "63cd50ec40sdawe";
    public static final String TESTID_INVALID = "testidInvalido";
    public static final String OBJECT_NOT_FOUND = "Object not Found: " + TESTID_INVALID + " , type: " +
            Author.class.getName();
    public static final String DESCRIPTION = "ANTOINE JEAN-BAPTISTE MARIE ROGER FOSCOLOMBE, popularmente conhecido como Antonie de Saint-Exupéry foi escritor, ilustrador e piloto francês.";
    public static final String NAME = "Antonie de Saint-Exupéry";
    public static final String NATIONALITY = "França";
    public static final LocalDate BIRTH_DATE = LocalDate.of(1900, Month.JUNE, 29);
    public static final LocalDate REGISTER_DATE = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")));
    public static final String DATA_VIOLATION_MESSAGE = "Error creating :" + Author.class.getName();


    @InjectMocks
    private AuthorService authorService;

    @Spy
    private AuthorRepository authorRepository;

    @Mock
    private Author author;

    Optional<Author> optionalAuthor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        initModels();
    }


    @Test
    void whenFindByIdThenReturnAnAuthorInstance() {

       when(authorRepository.findById(anyString())).thenReturn(optionalAuthor);

       Author response = authorService.findById(ID);

       assertNotNull(response);
       assertEquals(Author.class,response.getClass());
       assertEquals(ID,response.getId());
       assertEquals(NAME,response.getName());
       assertEquals(DESCRIPTION,response.getDescription());
       assertEquals(NATIONALITY,response.getNationality());
       assertEquals(BIRTH_DATE,response.getBirthDate());
       assertEquals(REGISTER_DATE,response.getRegisterDate());

    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){

        try{
            authorService.findById(TESTID_INVALID);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }


    }

    @Test
    void whenFindAllThenReturnAListOfAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(author));

        List<Author> response = authorService.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Author.class,response.get(0).getClass());
        assertEquals(ID,response.get(0).getId());

    }

    @Test
    void whenFindByNationalityThenReturnAnAuthorInstance() {
        when(authorRepository.findByNationality(anyString())).thenReturn(List.of(author));

        List<Author> response = authorService.findByNationality(NATIONALITY);

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Author.class,response.get(0).getClass());
        assertEquals(NATIONALITY,response.get(0).getNationality());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(authorRepository.save(any())).thenReturn(author);

        Author response = authorService.create(author);

        assertNotNull(response);
        assertEquals(Author.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(DESCRIPTION,response.getDescription());
        assertEquals(NATIONALITY,response.getNationality());
        assertEquals(BIRTH_DATE,response.getBirthDate());
        assertEquals(REGISTER_DATE,response.getRegisterDate());
    }
    @Test
    void whenCreateThenReturnDataViolationException() {

        when(authorRepository.save(any())).thenThrow(new DataViolationException(DATA_VIOLATION_MESSAGE));

        try {
            authorService.create(author);
        }catch (Exception ex){
            assertEquals(DataViolationException.class,ex.getClass());
            assertEquals(DATA_VIOLATION_MESSAGE,ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(authorRepository.findById(anyString())).thenReturn(optionalAuthor);
        when(authorRepository.save(any())).thenReturn(author);

        Author response = authorService.update(author);

        assertNotNull(response);
        assertEquals(Author.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(DESCRIPTION,response.getDescription());
        assertEquals(NATIONALITY,response.getNationality());
        assertEquals(BIRTH_DATE,response.getBirthDate());
        assertEquals(REGISTER_DATE,response.getRegisterDate());

    }

    @Test
    void whenUpdateThenReturnObjectNotFound() {
        when(authorRepository.findById(any())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            authorService.update(author);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(authorRepository.findById(anyString())).thenReturn(optionalAuthor);
        doNothing().when(authorRepository).delete(any());
        authorService.delete(ID);
        verify(authorRepository,times(1)).deleteById(any());

    }

    @Test
    void deleteWithObjectNotFound() {
        when(authorRepository.findById(any())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            authorService.delete(ID);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }
    }
    void initModels(){

        author = Author.builder().id(ID).description(DESCRIPTION).name(NAME)
                .nationality(NATIONALITY).birthDate(BIRTH_DATE).registerDate(REGISTER_DATE)
                .build();

        optionalAuthor = Optional.of(Author.builder().id(ID).description(DESCRIPTION).name(NAME)
                .nationality(NATIONALITY).birthDate(BIRTH_DATE).registerDate(REGISTER_DATE)
                .build());

    }

}