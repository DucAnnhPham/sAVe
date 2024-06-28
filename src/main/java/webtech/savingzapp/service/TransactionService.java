package webtech.savingzapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtech.savingzapp.model.Transaction;
import webtech.savingzapp.persistance.TransactionRepository;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> getTransaction(Long id) {
        return this.transactionRepository.findById(id);
    }

    public Iterable<Transaction> getTransactions() {
        return this.transactionRepository.findAll();
    }

    public Iterable<Transaction> getTransactionByUserId(Long userId) {
        return this.transactionRepository.findByUserId(userId);
    }

    public Transaction addTransaction(final Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    public Transaction editTransaction(final Transaction transaction) {
        return transactionRepository.existsById(transaction.getId())
                ? transactionRepository.save(transaction)
                : null;
    }

    public boolean removeTransaction(final Long id) {
        final boolean exists = transactionRepository.existsById(id);
        if (exists) transactionRepository.deleteById(id);
        return exists;
    }


}
