package com.atos.library.libraryregistry.controller;

import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.repository.GenreRepository;
import com.atos.library.libraryregistry.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class GenreControllerTest {
    public static final String ID = "stre7trtewyrtew212";
    public static final String NAME = "Romance";

    public static final String TESTID_INVALID = "testidInvalido";

    public static final String OBJECT_NOT_FOUND = "Object not Found: " + TESTID_INVALID + " , type: " +
            Genre.class.getName();

    public static final String DATA_VIOLATION_MESSAGE = "Error creating :" + Genre.class.getName();


    @InjectMocks
    private GenreController genreController;

    @Mock
    private GenreService genreService;
    @Mock
    private Genre genre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initModels();
    }


    @Test
    void whenFindByIdThenReturnOK() {
        when(genreService.findById(anyString())).thenReturn(genre);
        ResponseEntity<Genre> response = genreController.findById(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(genre,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenFindAllThenReturnOK() {
        when(genreService.findAll()).thenReturn(List.of(genre));
        ResponseEntity<List<Genre>> response = genreController.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(List.of(genre),response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

    }

    @Test
    void whenCreateThenReturnCreated() {

        when(genreService.create(any())).thenReturn(genre);
        ResponseEntity<Genre> response = genreController.create(genre);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(201,response.getStatusCodeValue());

    }

    @Test
    void whenUpdateThenReturnOK() {
        when(genreService.update(any())).thenReturn(genre);

        ResponseEntity<Genre> response = genreController.update(genre);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenDeleteThenReturnNoContent() {
        doNothing().when(genreService).delete(any());

        ResponseEntity<Genre> response = genreController.delete(ID);

        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        verify(genreService,times(1)).delete(any());
        assertEquals(204,response.getStatusCodeValue());
    }

    void initModels(){
        genre = new Genre(ID, NAME);
    }
}