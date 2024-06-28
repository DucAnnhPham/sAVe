package webtech.savingzapp.persistance;

import org.springframework.data.repository.CrudRepository;
import webtech.savingzapp.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Iterable<Transaction> findByUserId(Long userId);
}
