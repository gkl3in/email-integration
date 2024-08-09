package com.gabrielklein.email.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielklein.email.dto.EmailAwsDTO;
import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.dto.EmailOciDTO;
import com.gabrielklein.email.enums.EIntegrationType;
import com.gabrielklein.email.exceptions.ValidationDataIntegrityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.gabrielklein.email.mapper.EmailMapper.convertToAwsDTO;
import static com.gabrielklein.email.mapper.EmailMapper.convertToOciDTO;

@Service
public class EmailServices {

    @Value("${mail.integracao}")
    private String mailIntegracao;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void emailIntegration(EmailDTO emailDTO) {
        try {
            EIntegrationType eIntegrationType = EIntegrationType.fromString(mailIntegracao);
            String serializedEmail = serializeEmail(emailDTO, eIntegrationType);
            System.out.println(serializedEmail);
        } catch (JsonProcessingException e) {
            throw new com.gabrielklein.email.exceptions.JsonProcessingException("Erro ao processar JSON");
        }
    }

    private String serializeEmail(EmailDTO emailDTO, EIntegrationType integrationType) throws JsonProcessingException {
        return switch (integrationType) {
            case OCI -> {
                EmailOciDTO emailOciDTO = convertToOciDTO(emailDTO);
                yield objectMapper.writeValueAsString(emailOciDTO);
            }
            case AWS -> {
                EmailAwsDTO emailAwsDTO = convertToAwsDTO(emailDTO);
                yield objectMapper.writeValueAsString(emailAwsDTO);
            }
        };
    }
}
