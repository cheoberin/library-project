package com.atos.library.libraryregistry.controller.exceptions;

import com.atos.library.libraryregistry.service.exceptions.DataViolationException;
import com.atos.library.libraryregistry.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {
    public static final String OBJECT_NOT_FOUND = "Object Not Found";
    public static final String ERROR_CREATING = "Error creating";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void whenObjectNotFoundExceptionThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .ObjectNotFound(new ObjectNotFoundException(OBJECT_NOT_FOUND),
                        new MockHttpServletRequest());
        System.out.println(response.getBody().getPath());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(StandardError.class,response.getBody().getClass());
        assertEquals(OBJECT_NOT_FOUND,response.getBody().getError());
        assertEquals(404,response.getBody().getStatus());
        assertEquals(LocalDateTime.now(),response.getBody().getLocalDateTime());
    }

    @Test
    void whenDataIntegrityViolationThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .DataIntegrityViolation(new DataViolationException(ERROR_CREATING),
                        new MockHttpServletRequest());
        System.out.println(response.getBody().getPath());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(StandardError.class,response.getBody().getClass());
        assertEquals(ERROR_CREATING,response.getBody().getError());
        assertEquals(400,response.getBody().getStatus());
        assertEquals(LocalDateTime.now(),response.getBody().getLocalDateTime());

    }
}