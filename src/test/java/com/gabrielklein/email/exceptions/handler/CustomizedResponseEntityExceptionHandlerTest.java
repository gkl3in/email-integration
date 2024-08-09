package com.gabrielklein.email.exceptions.handler;

import com.gabrielklein.email.exceptions.ExceptionResponse;
import com.gabrielklein.email.exceptions.JsonProcessingException;
import com.gabrielklein.email.exceptions.ValidationDataIntegrityException;
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
class CustomizedResponseEntityExceptionHandlerTest {

    private static final String ERROR_JSON = "Erro ao processar JSON";
    private static final String ERROR_VALIDATION = "Teste de validação";
    private static final String ERROR_VALIDATION_ILLEGAL = "Teste de validação illegal";

    @InjectMocks
    private CustomizedResponseEntityExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenExceptionThenReturnAResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler
                .handleAllExceptions(
                        new Exception(ERROR_VALIDATION),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals(ERROR_VALIDATION, response.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatus());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenJsonProcessingExceptionThenReturnAResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler
                .handleJsonProcessingException(
                        new JsonProcessingException(ERROR_JSON),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals(ERROR_JSON, response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotEquals("/email/2", response.getBody().getDetails());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenValidationDataIntegrityExceptionThenReturnAResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler
                .handleValidationDataIntegrityException(
                        new ValidationDataIntegrityException(ERROR_VALIDATION),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals(ERROR_VALIDATION, response.getBody().getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getBody().getStatus());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenIllegalArgumentExceptionThenReturnAResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler
                .handleIllegalArgumentExceptions(
                        new IllegalArgumentException(ERROR_VALIDATION_ILLEGAL),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals(ERROR_VALIDATION_ILLEGAL, response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }
}