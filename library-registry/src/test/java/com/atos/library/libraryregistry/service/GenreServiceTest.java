package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.repository.GenreRepository;
import com.atos.library.libraryregistry.service.exceptions.DataViolationException;
import com.atos.library.libraryregistry.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class GenreServiceTest {

    public static final String ID = "stre7trtewyrtew212";
    public static final String NAME = "Romance";

    public static final String TESTID_INVALID = "testidInvalido";

    public static final String OBJECT_NOT_FOUND = "Object not Found: " + TESTID_INVALID + " , type: " +
            Genre.class.getName();

    public static final String DATA_VIOLATION_MESSAGE = "Error creating :" + Genre.class.getName();

    @InjectMocks
    private GenreService genreService;

    @Spy
    private GenreRepository genreRepository;
    @Mock
    private Genre genre;

    Optional<Genre> genreOptional;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initModels();
    }

    @Test
    void whenFindByIdThenReturnAnGenreInstance() {
        when(genreRepository.findById(anyString())).thenReturn(genreOptional);

        Genre response = genreService.findById(ID);

        assertNotNull(response);
        assertEquals(Genre.class,response.getClass());
        assertEquals(ID,response.getId());


    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){

        try{
            genreService.findById(TESTID_INVALID);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }


    }

    @Test
    void whenFindAllThenReturnAListOfGenres() {
        when(genreRepository.findAll()).thenReturn(List.of(genre));

        List<Genre> response = genreService.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Genre.class,response.get(0).getClass());
        assertEquals(ID,response.get(0).getId());

    }


    @Test
    void whenCreateThenReturnSuccess() {
        when(genreRepository.save(any())).thenReturn(genre);

        Genre response = genreService.create(genre);

        assertNotNull(response);
        assertEquals(Genre.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());

    }
    @Test
    void whenCreateThenReturnDataViolationException() {

        when(genreRepository.save(any())).thenThrow(new DataViolationException(DATA_VIOLATION_MESSAGE));

        try {
            genreService.create(genre);
        }catch (Exception ex){
            assertEquals(DataViolationException.class,ex.getClass());
            assertEquals(DATA_VIOLATION_MESSAGE,ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(genreRepository.findById(anyString())).thenReturn(genreOptional);
        when(genreRepository.save(any())).thenReturn(genre);

        Genre response = genreService.update(genre);

        assertNotNull(response);
        assertEquals(Genre.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());


    }

    @Test
    void whenUpdateThenReturnObjectNotFound() {
        when(genreRepository.findById(any())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            genreService.update(genre);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(genreRepository.findById(anyString())).thenReturn(genreOptional);
        doNothing().when(genreRepository).delete(any());
        genreService.delete(ID);
        verify(genreRepository,times(1)).deleteById(any());

    }

    @Test
    void deleteWithObjectNotFound() {
        when(genreRepository.findById(any())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            genreService.delete(ID);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }
    }
    void initModels(){
        genre = new Genre(ID, NAME);
        genreOptional = Optional.of(new Genre(ID, NAME));

    }
}