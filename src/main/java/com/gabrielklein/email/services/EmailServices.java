package com.gabrielklein.email.services;

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

    public void update(EmailDTO emailDTO) {
        if ("OCI".equalsIgnoreCase(mailIntegracao)) {
            EmailOciDTO emailOciDTO = convertToOciDTO(emailDTO);
            System.out.println("aa");
        } else if ("AWS".equalsIgnoreCase(mailIntegracao)) {
            EmailAwsDTO emailAwsDTO = convertToAwsDTO(emailDTO);
            System.out.println("aa");
        } else {
            throw new IllegalArgumentException("Tipo de integração desconhecido: " + mailIntegracao);
        }
    }
}
