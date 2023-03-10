package com.atos.library.libraryregistry.controller;

import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class AuthorControllerTest {

    public static final String ID                     = "63cd50ec40sdawe";
    public static final String TESTID_INVALID         = "testidInvalido";
    public static final String OBJECT_NOT_FOUND       = "Object not Found: " + TESTID_INVALID + " , type: " + Author.class.getName();
    public static final String DESCRIPTION            = "ANTOINE JEAN-BAPTISTE MARIE ROGER FOSCOLOMBE, popularmente conhecido como Antonie de Saint-Exupéry foi escritor, ilustrador e piloto francês.";
    public static final String NAME                   = "Antonie de Saint-Exupéry";
    public static final String NATIONALITY            = "França";
    public static final LocalDate BIRTH_DATE          = LocalDate.of(1900, Month.JUNE, 29);
    public static final LocalDate REGISTER_DATE       = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")));
    public static final String DATA_VIOLATION_MESSAGE = "Error creating :" + Author.class.getName();

    @InjectMocks
    private AuthorController authorController;
    @Mock
    private AuthorService authorService;
    @Mock
    private Author author;

    @BeforeEach
    void setUp() {
        openMocks(this);
        initModels();
    }

    @Test
    void whenFindByIdThenReturnOK() {
        when(authorService.findById(anyString())).thenReturn(author);
        ResponseEntity<Author> response = authorController.findById(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(author,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenFindAllThenReturnOK() {
        when(authorService.findAll()).thenReturn(List.of(author));

        ResponseEntity<List<Author>> response = authorController.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(List.of(author),response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

    }

    @Test
    void whenfindByNationalityThenreturnOK() {
        when(authorService.findByNationality(anyString())).thenReturn(List.of(author));

        ResponseEntity<List<Author>> response = authorController.findByNationality(NATIONALITY);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(List.of(author),response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenCreateThenReturnCreated() {


        when(authorService.create(any())).thenReturn(author);
        ResponseEntity<Author> response = authorController.create(author);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(201,response.getStatusCodeValue());

    }

    @Test
    void whenUpdateThenReturnOK() {
        when(authorService.update(any())).thenReturn(author);

        ResponseEntity<Author> response = authorController.update(author);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenDeleteThenReturnNoContent() {
        doNothing().when(authorService).delete(any());

        ResponseEntity<Author> response = authorController.delete(ID);

        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        verify(authorService,times(1)).delete(any());
        assertEquals(204,response.getStatusCodeValue());
    }

    void initModels(){

        author = Author.builder().id(ID).description(DESCRIPTION).name(NAME)
                .nationality(NATIONALITY).birthDate(BIRTH_DATE).registerDate(REGISTER_DATE)
                .build();

    }
}