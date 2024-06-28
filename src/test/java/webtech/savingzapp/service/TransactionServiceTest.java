package webtech.savingzapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import webtech.savingzapp.model.Transaction;
import webtech.savingzapp.persistance.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void testGetTransaction() {

        //Eingabedaten
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100), 1L);
        var t2 = new Transaction("Test2", "Test2", LocalDate.of(2024,6,23), BigDecimal.valueOf(101),1L);

        //Mocking
        doReturn(Optional.of(t1)).when(transactionRepository).findById(1L);
        doReturn(Optional.of(t2)).when(transactionRepository).findById(2L);

        //erwartetes Ergebnis
        Transaction actual = transactionService.getTransaction(1L).get();

        //Vergleich
        assertEquals(t1.getTransactionName(), actual.getTransactionName());
    }

    @Test
    void testGetAllTransactions() {

        //Eingabedaten
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100), 1L);
        var t2 = new Transaction("Test2", "Test2", LocalDate.of(2024,6,23), BigDecimal.valueOf(101),2L);

        //Mocking
        doReturn(Arrays.asList(t1, t2)).when(transactionRepository).findAll();

        //erwartetes Ergebnis
        Iterable<Transaction> actual = transactionService.getTransactions();

        //Vergleich
        assertEquals(2, ((java.util.Collection<?>) actual).size());
    }

    @Test
    void testGetTransactionByUserId() {

        //Eingabedaten
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100), 1L);
        var t2 = new Transaction("Test2", "Test2", LocalDate.of(2024,6,23), BigDecimal.valueOf(101),1L);

        //Mocking
        doReturn(Arrays.asList(t1, t2)).when(transactionRepository).findByUserId(1L);

        //erwartetes Ergebnis
        Iterable<Transaction> actual = transactionService.getTransactionByUserId(1L);

        //Vergleich
        assertEquals(2, ((java.util.Collection<?>) actual).size());
    }

    @Test
    void testAddTransaction() {

        //Eingabedaten
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100), 1L);

        //Mocking
        doReturn(t1).when(transactionRepository).save(t1);

        //erwartetes Ergebnis
        Transaction actual = transactionService.addTransaction(t1);

        //Vergleich
        assertEquals(t1.getTransactionName(), actual.getTransactionName());
    }

    @Test
    void testEditTransaction() {
        // Eingabedaten
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100), 1L);
        t1.setId(1L);
        // neue Daten
        var t2 = new Transaction("Test Edited", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100), 1L);
        t2.setId(1L);

        // Mocking
        doReturn(true).when(transactionRepository).existsById(t1.getId());
        doReturn(t2).when(transactionRepository).save(t2);
        Transaction actual = transactionService.editTransaction(t2);

        // Vergleich
        assertEquals(t2.getTransactionName(), actual.getTransactionName());
    }

    @Test
    void testRemoveTransaction() {

        //Eingabedaten
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100), 1L);
        t1.setId(1L);

        //Mocking
        doReturn(true).when(transactionRepository).existsById(1L);
        boolean actual = transactionService.removeTransaction(1L);

        assertTrue(actual);
        verify(transactionRepository, times(1)).deleteById(1L);
    }
}