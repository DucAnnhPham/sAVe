package webtech.savingzapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import webtech.savingzapp.model.Transaction;
import webtech.savingzapp.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService service;

    @BeforeEach
    void setUpMockRepository() {
        final Transaction t1 = new Transaction("wasser", "Food", LocalDate.of(2021, 1, 1), BigDecimal.valueOf(1.5), 1L);
        t1.setId(1L);
        when(service.getTransaction(1L)).thenReturn(Optional.of(t1));
    }

    @Test
    void testGetTransactionById() throws Exception {

        //erwartetes ergebnis
        final String expected = "{\"id\":1,\"transactionName\":\"wasser\",\"transactionCategory\":\"Food\",\"transactionDate\":[2021,1,1],\"transactionAmount\":1.50,\"userId\":1}";

        //tatächliches ergebnis/ausführung
        this.mockMvc.perform(get("/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(equalTo(expected)));
    }

    @Test
    void testGetTransactionByIdNotFound() throws Exception {
        //ergebnis sollte not found sein
        this.mockMvc.perform(get("/transactions/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTransactionByIdBadRequest() throws Exception {
        //ergebnis sollte bad request sein
        this.mockMvc.perform(get("/transactions/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAddTransaction() throws Exception {

        //erwartetes ergebnis
        final Transaction t1 = new Transaction("wasser", "Food", LocalDate.of(2021, 1, 1), BigDecimal.valueOf(1.5), 1L);
        final String json = objectMapper.writeValueAsString(t1);

        //tatächliches ergebnis/ausführung
        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateTransaction_Success() throws Exception {

        //Eingabedaten
        Transaction originalTransaction = new Transaction("wasser", "Food", LocalDate.of(2021, 1, 1), BigDecimal.valueOf(1.5), 1L);
        originalTransaction.setId(1L);

        Transaction updatedTransaction = new Transaction("cola", "Food", LocalDate.of(2021, 1, 1), BigDecimal.valueOf(2.0), 1L);
        updatedTransaction.setId(originalTransaction.getId());

        String json = objectMapper.writeValueAsString(updatedTransaction);

        //Mocking
        when(service.editTransaction(any(Transaction.class))).thenReturn(updatedTransaction);

        //Tatsächliches Ergebnis/ausführung
        mockMvc.perform(MockMvcRequestBuilders.put("/transactions/{id}", originalTransaction.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteTransaction() throws Exception {

        //Eingabedaten
        final Transaction t1 = new Transaction("wasser", "Food", LocalDate.of(2021, 1, 1), BigDecimal.valueOf(1.5), 1L);
        t1.setId(1L);

        //Mocking
        when(service.removeTransaction(1L)).thenReturn(true);

        //Tatsächliches Ergebnis/ausführung
        mockMvc.perform(MockMvcRequestBuilders.delete("/transactions/{id}", t1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}