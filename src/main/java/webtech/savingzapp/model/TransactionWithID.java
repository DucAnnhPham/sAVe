package webtech.savingzapp.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionWithID extends Transaction{

    private long id;

    public TransactionWithID(String transactionName, String transactionCategory, LocalDate transactionDate, BigDecimal transactionAmount, long id) {
        super(transactionName, transactionCategory, transactionDate, transactionAmount);
        this.id = id;
    }
}
