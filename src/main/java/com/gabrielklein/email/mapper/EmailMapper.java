package com.gabrielklein.email.mapper;

import com.gabrielklein.email.dto.EmailAwsDTO;
import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.dto.EmailOciDTO;

public final class EmailMapper {

    public static EmailOciDTO convertToOciDTO(EmailDTO emailDTO) {
        return new EmailOciDTO(
                emailDTO.getRecipient(),
                emailDTO.getRecipientName(),
                emailDTO.getSender(),
                emailDTO.getSubject(),
                emailDTO.getContent()
        );
    }

    public static EmailAwsDTO convertToAwsDTO(EmailDTO emailDTO) {
        return new EmailAwsDTO(
                emailDTO.getRecipient(),
                emailDTO.getRecipientName(),
                emailDTO.getSender(),
                emailDTO.getSubject(),
                emailDTO.getContent()
        );
    }
}
