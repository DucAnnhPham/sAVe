package webtech.savingzapp.service;

import org.springframework.stereotype.Service;
import webtech.savingzapp.model.Transaction;
import webtech.savingzapp.model.TransactionWithID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class TransactionService {

    private final HashMap<Long, TransactionWithID> data = new HashMap<>()
    {{
        put(1L, new TransactionWithID("Groceries", "Food", LocalDate.of(2024,04,19), new BigDecimal("56.73"), 1L));
        put(2L, new TransactionWithID("Clothes", "Clothing", LocalDate.of(2024,04,18), new BigDecimal("76.95"), 2L));
        put(3L, new TransactionWithID("Netflix", "Subscriptions",  LocalDate.of(2024,04,01), new BigDecimal("13.99"), 3L));
        put(4L, new TransactionWithID("Salary", "Income", LocalDate.of(2024,03,31), new BigDecimal("2000.00"), 4L));
        put(5L, new TransactionWithID("Rent", "Housing", LocalDate.of(2024,03,31), new BigDecimal("1000.00"), 5L));
    }};

    private long currentId = data.size() + 1;

    public TransactionWithID getTransaction(long id) {
        return data.get(id);
    }

    public List<TransactionWithID> getTransactions(){
        return data.values().stream().toList();
    }

    public TransactionWithID addTransaction(final Transaction transaction) {
        final long id = currentId++;
        final TransactionWithID transactionWithId = new TransactionWithID(transaction.getTransactionName(), transaction.getTransactionCategory(), transaction.getTransactionDate(), transaction.getTransactionAmount(), id);
        data.put(id, transactionWithId);
        return transactionWithId;
    }

    public TransactionWithID editTransaction(final long id, final Transaction transaction) {
        if (!data.containsKey(id)) return null;
        final TransactionWithID transactionToEdit = data.get(id);
        transactionToEdit.setTransactionName(transaction.getTransactionName());
        transactionToEdit.setTransactionCategory(transaction.getTransactionCategory());
        transactionToEdit.setTransactionDate(transaction.getTransactionDate());
        transactionToEdit.setTransactionAmount(transaction.getTransactionAmount());
        return transactionToEdit;
    }

    public boolean removeTransaction(final Long id) {
        return data.remove(id) != null;
    }





}
