package controller;

import hexagonalArchitecture.adapters.web.BankAccountController;
import hexagonalArchitecture.application.services.BankAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BankAccountController.class)
@AutoConfigureMockMvc
public class BankAccountControllerTest {
    @MockBean
    private BankAccountService accountService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_accepted_status_when_deposit () throws Exception {
        mockMvc.perform(post("/account/{id}/deposit/{amount}", 1L, BigDecimal.valueOf(100))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void should_return_accepted_status_when_withDraw () throws Exception {
        mockMvc.perform(post("/account/{id}/withdraw/{amount}", 1L, BigDecimal.valueOf(100))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void should_return_ok_status_when_history () throws Exception {
        mockMvc.perform(post("/account/{id}/history", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
