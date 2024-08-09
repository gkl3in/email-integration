package com.gabrielklein.email.controller;

import com.gabrielklein.email.controllers.EmailController;
import com.gabrielklein.email.dto.EmailDTO;
import com.gabrielklein.email.services.EmailServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailServices emailService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }

    @Test
    void whenPostEmailThenReturnNoContent() throws Exception {
        doNothing().when(emailService).emailIntegration(any(EmailDTO.class));

        String emailJson = """
                {
                    "recipient": "recipient@example.com",
                    "recipientName": "Recipient Name",
                    "sender": "sender@example.com",
                    "subject": "Test Subject",
                    "content": "Test Content"
                }
                """;

        mockMvc.perform(post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emailJson))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }
}
