package com.gabrielklein.email.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielklein.email.dto.EmailAwsDTO;
import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.dto.EmailOciDTO;
import com.gabrielklein.email.exceptions.ValidationDataIntegrityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServicesTest {

    @InjectMocks
    private EmailServices emailServices;

    @Mock
    private ObjectMapper objectMapper;

    private EmailDTO validEmailDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validEmailDTO = new EmailDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );
    }

    @Test
    void whenIntegrationTypeIsOCIThenSerializeOciDTO() throws JsonProcessingException {
        ReflectionTestUtils.setField(emailServices, "mailIntegracao", "OCI");

        when(objectMapper.writeValueAsString(any(EmailOciDTO.class))).thenReturn("Serialized OCI Email");

        emailServices.emailIntegration(validEmailDTO);

        verify(objectMapper, times(1)).writeValueAsString(any(EmailOciDTO.class));
    }

    @Test
    void whenIntegrationTypeIsAWSThenSerializeAwsDTO() throws JsonProcessingException {
        ReflectionTestUtils.setField(emailServices, "mailIntegracao", "AWS");

        when(objectMapper.writeValueAsString(any(EmailAwsDTO.class))).thenReturn("Serialized AWS Email");

        emailServices.emailIntegration(validEmailDTO);

        verify(objectMapper, times(1)).writeValueAsString(any(EmailAwsDTO.class));
    }

    @Test
    void whenJsonProcessingExceptionThenThrowCustomException() throws JsonProcessingException {
        ReflectionTestUtils.setField(emailServices, "mailIntegracao", "AWS");

        when(objectMapper.writeValueAsString(any(EmailAwsDTO.class)))
                .thenThrow(new JsonProcessingException("Erro de processamento") {});

        com.gabrielklein.email.exceptions.JsonProcessingException exception = assertThrows(com.gabrielklein.email.exceptions.JsonProcessingException.class, () -> {
            emailServices.emailIntegration(validEmailDTO);
        });

        assertEquals("Erro ao processar JSON", exception.getMessage());
    }

    @Test
    void whenInvalidIntegrationTypeThenThrowValidationDataIntegrityException() {
        ReflectionTestUtils.setField(emailServices, "mailIntegracao", "INVALID_TYPE");

        assertThrows(IllegalArgumentException.class, () -> {
            emailServices.emailIntegration(validEmailDTO);
        });
    }
}
