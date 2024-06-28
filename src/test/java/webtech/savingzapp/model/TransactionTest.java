package webtech.savingzapp.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testToString() {
        //Eingabedaten
        String Name = "Flat";
        String Category = "Rent";
        LocalDate Date  = LocalDate.parse("2021-01-01");
        BigDecimal Amount = BigDecimal.valueOf(1000);
        Long UserId = 1L;

        //System under Test aufsetzen
        Transaction transaction = new Transaction(Name,Category,Date,Amount,UserId);
        transaction.setId(10L);

        //erwartetets Ergebnis
        String expected = "Transaction{id=10, transactionName='Flat', transactionCategory='Rent', transactionDate=2021-01-01, transactionAmount=1000.00, userId=1}";

        //tatächliches Ergebnis
        String actual = transaction.toString();

        //Vergleich
        assertEquals(expected,actual);
    }

}