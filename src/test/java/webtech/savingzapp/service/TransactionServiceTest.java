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
import static org.mockito.Mockito.doReturn;


@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void testGetTransaction() {
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100));
        var t2 = new Transaction("Test2", "Test2", LocalDate.of(2024,6,23), BigDecimal.valueOf(101));

        doReturn(Optional.of(t1)).when(transactionRepository).findById(1L);
        doReturn(Optional.of(t2)).when(transactionRepository).findById(2L);

        Transaction actual = transactionService.getTransaction(1L).get();

        assertEquals(t1.getTransactionName(), actual.getTransactionName());
    }

    @Test
    void testGetAllTransactions() {
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100));
        var t2 = new Transaction("Test2", "Test2", LocalDate.of(2024,6,23), BigDecimal.valueOf(101));

        doReturn(Arrays.asList(t1, t2)).when(transactionRepository).findAll();

        Iterable<Transaction> actual = transactionService.getTransactions();

        assertEquals(2, ((java.util.Collection<?>) actual).size());
    }

    @Test
    void testAddTransaction() {
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100));

        doReturn(t1).when(transactionRepository).save(t1);

        Transaction actual = transactionService.addTransaction(t1);

        assertEquals(t1.getTransactionName(), actual.getTransactionName());
    }

    @Test
    void testEditTransaction() {
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100));

        t1.setId(1L);

        doReturn(true).when(transactionRepository).existsById(t1.getId());
        doReturn(t1).when(transactionRepository).save(t1);

        Transaction actual = transactionService.editTransaction(t1);

        assertEquals(t1.getTransactionName(), actual.getTransactionName());

    }

    @Test
    void testRemoveTransaction() {
        var t1 = new Transaction("Test", "Test", LocalDate.of(2024,6,22), BigDecimal.valueOf(100));

        doReturn(true).when(transactionRepository).existsById(1L);

        boolean actual = transactionService.removeTransaction(1L);

        assertEquals(true, actual);
    }
}