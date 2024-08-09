package com.gabrielklein.email.controllers;

import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.services.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailServices emailService;

    @PostMapping
    public ResponseEntity<?> emailIntegration(@RequestBody EmailDTO emailDTO) {
        emailService.emailIntegration(emailDTO);
        return ResponseEntity.noContent().build();
    }
}
