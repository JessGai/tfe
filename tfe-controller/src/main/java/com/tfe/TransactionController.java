package com.tfe;

import com.tfe.dto.TransactionDTO;
import com.tfe.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/{id}")
    public TransactionDTO getTransactionById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public TransactionDTO createTransaction(@Valid @RequestBody TransactionDTO dto) {
        return transactionService.createTransaction(dto);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Void> updateStatut(@PathVariable int id, @RequestBody TransactionDTO dto) {
        transactionService.updateStatut(id, dto.getStatut());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/open/{idParent}")
    public TransactionDTO getOrCreateOpenTransaction(@PathVariable int idParent) {
        return transactionService.getOrCreateOpenTransaction(idParent);
    }

}
