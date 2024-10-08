package com.gabrielklein.email.mapper;

import com.gabrielklein.email.dto.EmailAwsDTO;
import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.dto.EmailOciDTO;
import com.gabrielklein.email.exceptions.ValidationDataIntegrityException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public final class EmailMapper {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static EmailOciDTO convertToOciDTO(EmailDTO emailDTO) {
        EmailOciDTO emailOciDTO = new EmailOciDTO(
                emailDTO.getRecipient(),
                emailDTO.getRecipientName(),
                emailDTO.getSender(),
                emailDTO.getSubject(),
                emailDTO.getContent()
        );

        validate(emailOciDTO);
        return emailOciDTO;
    }

    public static EmailAwsDTO convertToAwsDTO(EmailDTO emailDTO) {
        EmailAwsDTO emailAwsDTO = new EmailAwsDTO(
                emailDTO.getRecipient(),
                emailDTO.getRecipientName(),
                emailDTO.getSender(),
                emailDTO.getSubject(),
                emailDTO.getContent()
        );

        validate(emailAwsDTO);
        return emailAwsDTO;
    }

    private static <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(violation -> "Inconsistência encontrada no campo " + violation.getPropertyPath() + ": " + violation.getMessage())
                    .findFirst()
                    .orElse("Erro de validação desconhecido.");
            throw new ValidationDataIntegrityException(errorMessage);
        }
    }

}
