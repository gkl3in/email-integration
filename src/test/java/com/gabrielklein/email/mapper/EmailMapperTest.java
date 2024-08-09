package com.gabrielklein.email.mapper;

import com.gabrielklein.email.dto.EmailAwsDTO;
import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.dto.EmailOciDTO;
import com.gabrielklein.email.exceptions.ValidationDataIntegrityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailMapperTest {

    private EmailDTO validEmailDTO;

    @BeforeEach
    void setUp() {
        validEmailDTO = new EmailDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );
    }

    @Test
    void whenValidEmailDTOThenConvertToOciDTO() {
        EmailOciDTO emailOciDTO = EmailMapper.convertToOciDTO(validEmailDTO);

        assertNotNull(emailOciDTO);
        assertEquals(validEmailDTO.getRecipient(), emailOciDTO.getRecipientEmail());
        assertEquals(validEmailDTO.getRecipientName(), emailOciDTO.getRecipientName());
        assertEquals(validEmailDTO.getSender(), emailOciDTO.getSenderEmail());
        assertEquals(validEmailDTO.getSubject(), emailOciDTO.getSubject());
        assertEquals(validEmailDTO.getContent(), emailOciDTO.getBody());
    }

    @Test
    void whenValidEmailDTOThenConvertToAwsDTO() {
        EmailAwsDTO emailAwsDTO = EmailMapper.convertToAwsDTO(validEmailDTO);

        assertNotNull(emailAwsDTO);
        assertEquals(validEmailDTO.getRecipient(), emailAwsDTO.getRecipient());
        assertEquals(validEmailDTO.getRecipientName(), emailAwsDTO.getRecipientName());
        assertEquals(validEmailDTO.getSender(), emailAwsDTO.getSender());
        assertEquals(validEmailDTO.getSubject(), emailAwsDTO.getSubject());
        assertEquals(validEmailDTO.getContent(), emailAwsDTO.getContent());
    }

    @Test
    void whenInvalidEmailDTOThenThrowValidationDataIntegrityException() {
        EmailDTO invalidEmailDTO = new EmailDTO(
                "",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        assertThrows(ValidationDataIntegrityException.class, () -> {
            EmailMapper.convertToOciDTO(invalidEmailDTO);
        });

        assertThrows(ValidationDataIntegrityException.class, () -> {
            EmailMapper.convertToAwsDTO(invalidEmailDTO);
        });
    }

    @Test
    void whenEmptyEmailDTOThenThrowValidationDataIntegrityException() {
        EmailDTO emptyEmailDTO = new EmailDTO(
                "",
                "",
                "",
                "",
                ""
        );

        assertThrows(ValidationDataIntegrityException.class, () -> {
            EmailMapper.convertToOciDTO(emptyEmailDTO);
        });

        assertThrows(ValidationDataIntegrityException.class, () -> {
            EmailMapper.convertToAwsDTO(emptyEmailDTO);
        });
    }
}
