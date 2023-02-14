package com.atos.library.libraryregistry.controller;

import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.model.Publisher;
import com.atos.library.libraryregistry.repository.PublisherRepository;
import com.atos.library.libraryregistry.service.PublisherService;
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
class PublisherControllerTest {
    public static final String ID = "stre754677522r";
    public static final String NAME = "Milenium";

    public static final String TESTID_INVALID = "testidInvalido";

    public static final String OBJECT_NOT_FOUND = "Object not Found: " + TESTID_INVALID + " , type: " +
            Publisher.class.getName();

    public static final String DATA_VIOLATION_MESSAGE = "Error creating :" + Publisher.class.getName();

    @InjectMocks
    private PublisherController publisherController;

    @Mock
    private PublisherService publisherService;

    @Mock
    private Publisher publisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initModels();
    }

    @Test
    void whenFindByIdThenReturnOK() {
        when(publisherService.findById(anyString())).thenReturn(publisher);
        ResponseEntity<Publisher> response = publisherController.findById(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(publisher,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenFindAllThenReturnOK() {
        when(publisherService.findAll()).thenReturn(List.of(publisher));
        ResponseEntity<List<Publisher>> response = publisherController.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(List.of(publisher),response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

    }

    @Test
    void whenCreateThenReturnCreated() {

        when(publisherService.create(any())).thenReturn(publisher);
        ResponseEntity<Publisher> response = publisherController.create(publisher);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(201,response.getStatusCodeValue());

    }

    @Test
    void whenUpdateThenReturnOK() {
        when(publisherService.update(any())).thenReturn(publisher);

        ResponseEntity<Publisher> response = publisherController.update(publisher);

        assertNotNull(response);
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void whenDeleteThenReturnNoContent() {
        doNothing().when(publisherService).delete(any());

        ResponseEntity<Publisher> response = publisherController.delete(ID);

        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        verify(publisherService,times(1)).delete(any());
        assertEquals(204,response.getStatusCodeValue());
    }

    void initModels(){
        publisher = new Publisher(ID, NAME);
    }
}