package webtech.savingzapp.model;


import jakarta.persistence.*;
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

    private LocalDate transactionDate;

    private BigDecimal transactionAmount;

    private Long userId;

    public Transaction(String transactionName,String transactionCategory,LocalDate transactionDate, BigDecimal transactionAmount, Long userId) {
        this.transactionName = transactionName;
        this.transactionCategory = transactionCategory;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.userId = userId;
    }
    public Transaction() {}

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionName='" + transactionName + '\'' +
                ", transactionCategory='" + transactionCategory + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionAmount=" + transactionAmount +
                ", userId=" + userId +
                '}';
    }
}
