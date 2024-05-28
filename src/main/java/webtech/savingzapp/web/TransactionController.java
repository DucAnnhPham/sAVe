package webtech.savingzapp.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import webtech.savingzapp.model.Transaction;
import webtech.savingzapp.model.TransactionWithID;
import webtech.savingzapp.service.TransactionService;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionWithID>> getTransaction() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionWithID> getTransaction(@PathVariable("id") final long id){
        final TransactionWithID found = transactionService.getTransaction(id);
        return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<URI> addTransaction(@Valid @RequestBody Transaction body){
        final TransactionWithID createdTransaction = transactionService.addTransaction(body);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + createdTransaction.getId()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionWithID> updateTransaction(@PathVariable("id") final long id, @RequestBody Transaction body){
        final TransactionWithID updatedTransaction = transactionService.editTransaction(id, body);
        if(updatedTransaction == null) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") final long id){
        return transactionService.removeTransaction(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
