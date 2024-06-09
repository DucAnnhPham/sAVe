package webtech.savingzapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a transaction with a name, category, date and amount.
 */
@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionName;

    private String transactionCategory;

    private BigDecimal transactionAmount;

    private LocalDate transactionDate;

    public Transaction(String transactionName,String transactionCategory,LocalDate transactionDate, BigDecimal transactionAmount) {
        this.transactionName = transactionName;
        this.transactionCategory = transactionCategory;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    public Transaction() {}
}
