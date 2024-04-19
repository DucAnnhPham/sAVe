package webtech.savingzapp;


import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a transaction with a name, category, date and amount.
 */
@Getter
public class Transaction {

    private String transactionName;

    private String transactionCategory;

    private final BigDecimal transactionAmount;

    private final LocalDate transactionDate;

    public Transaction(String transactionName,String transactionCategory,LocalDate transactionDate, BigDecimal transactionAmount) {
        this.transactionName = transactionName;
        this.transactionCategory = transactionCategory;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
