package webtech.savingzapp.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import webtech.savingzapp.model.Transaction;
import webtech.savingzapp.service.TransactionService;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Transaction>> getTransaction() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") final long id){
        final Optional<Transaction> found = transactionService.getTransaction(id);
        return found.isPresent() ? ResponseEntity.ok(found.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody Transaction body){
        final Transaction t = new Transaction(body.getTransactionName(), body.getTransactionCategory(), body.getTransactionDate(), body.getTransactionAmount());
        final Transaction createdTransaction = transactionService.addTransaction(body);
        return new ResponseEntity<> (createdTransaction, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") final long id, @RequestBody Transaction body){
        body.setId(id);
        final Transaction updatedTransaction = transactionService.editTransaction(body);
        if(updatedTransaction == null) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") final long id){
        return transactionService.removeTransaction(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
