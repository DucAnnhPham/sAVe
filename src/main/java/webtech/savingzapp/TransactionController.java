package webtech.savingzapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController {
    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        Transaction t1 = new Transaction("Groceries", "Food", LocalDate.of(2024,04,19), new BigDecimal("56.73"));
        Transaction t2 = new Transaction("Clothes", "Clothing", LocalDate.of(2024,04,18), new BigDecimal("76.95"));
        Transaction t3 = new Transaction("Netflix", "Subscriptions", LocalDate.of(2024,04,01), new BigDecimal("13.99"));
        Transaction t4 = new Transaction("Salary", "Income", LocalDate.of(2024,03,31), new BigDecimal("2000.00"));
        Transaction t5 = new Transaction("Rent", "Housing", LocalDate.of(2024,03,31), new BigDecimal("1000.00"));
        return List.of(t1, t2, t3, t4, t5);
    }
}
