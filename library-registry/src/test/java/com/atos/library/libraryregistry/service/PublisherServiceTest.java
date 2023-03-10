package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Publisher;
import com.atos.library.libraryregistry.repository.PublisherRepository;
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
class PublisherServiceTest {

    public static final String ID = "stre754677522r";
    public static final String NAME = "Milenium";

    public static final String TESTID_INVALID = "testidInvalido";

    public static final String OBJECT_NOT_FOUND = "Object not Found: " + TESTID_INVALID + " , type: " +
            Publisher.class.getName();

    public static final String DATA_VIOLATION_MESSAGE = "Error creating :" + Publisher.class.getName();


    @InjectMocks
    private PublisherService publisherService;

    @Spy
    private PublisherRepository publisherRepository;

    @Mock
    private Publisher publisher;

    Optional<Publisher> publisherOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initModels();
    }

    @Test
    void whenFindByIdThenReturnAnPublisherInstance() {
        when(publisherRepository.findById(anyString())).thenReturn(publisherOptional);

        Publisher response = publisherService.findById(ID);

        assertNotNull(response);
        assertEquals(Publisher.class,response.getClass());
        assertEquals(ID,response.getId());


    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){

        try{
            publisherService.findById(TESTID_INVALID);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }


    }

    @Test
    void whenFindAllThenReturnAListOfPublishers() {
        when(publisherRepository.findAll()).thenReturn(List.of(publisher));

        List<Publisher> response = publisherService.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Publisher.class,response.get(0).getClass());
        assertEquals(ID,response.get(0).getId());

    }


    @Test
    void whenCreateThenReturnSuccess() {
        when(publisherRepository.save(any())).thenReturn(publisher);

       Publisher response = publisherService.create(publisher);

        assertNotNull(response);
        assertEquals(Publisher.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());

    }
    @Test
    void whenCreateThenReturnDataViolationException() {

        when(publisherRepository.save(any())).thenThrow(new DataViolationException(DATA_VIOLATION_MESSAGE));

        try {
            publisherService.create(publisher);
        }catch (Exception ex){
            assertEquals(DataViolationException.class,ex.getClass());
            assertEquals(DATA_VIOLATION_MESSAGE,ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(publisherRepository.findById(anyString())).thenReturn(publisherOptional);
        when(publisherRepository.save(any())).thenReturn(publisher);

        Publisher response = publisherService.update(publisher);

        assertNotNull(response);
        assertEquals(Publisher.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());


    }

    @Test
    void whenUpdateThenReturnObjectNotFound() {
        when(publisherRepository.findById(any())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            publisherService.update(publisher);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(publisherRepository.findById(anyString())).thenReturn(publisherOptional);
        doNothing().when(publisherRepository).delete(any());
        publisherService.delete(ID);
        verify(publisherRepository,times(1)).deleteById(any());

    }

    @Test
    void deleteWithObjectNotFound() {
        when(publisherRepository.findById(any())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            publisherService.delete(ID);
        }
        catch(Exception ex){
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECT_NOT_FOUND,ex.getMessage());
        }
    }

    void initModels(){
        publisher = new Publisher(ID, NAME);
        publisherOptional = Optional.of(new Publisher(ID, NAME));

    }
}