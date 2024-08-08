package com.gabrielklein.email.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielklein.email.dto.EmailAwsDTO;
import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.dto.EmailOciDTO;
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
        if ("OCI".equalsIgnoreCase(mailIntegracao)) {
            EmailOciDTO emailOciDTO = convertToOciDTO(emailDTO);
            try {
                String serializedEmailOciDTO = objectMapper.writeValueAsString(emailOciDTO);
                System.out.println(serializedEmailOciDTO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if ("AWS".equalsIgnoreCase(mailIntegracao)) {
            EmailAwsDTO emailAwsDTO = convertToAwsDTO(emailDTO);
            try {
                String serializedEmailAwsDTO = objectMapper.writeValueAsString(emailAwsDTO);
                System.out.println(serializedEmailAwsDTO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(emailAwsDTO);
        } else {
            throw new IllegalArgumentException("Tipo de integração desconhecido: " + mailIntegracao);
        }
    }
}
