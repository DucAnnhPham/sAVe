package webtech.savingzapp.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a transaction with a name, category, date and amount.
 */
@Getter
@Setter
public class Transaction {

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


}
