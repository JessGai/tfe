package com.tfe;

import com.tfe.dto.PanierDTO;
import com.tfe.dto.TransactionDTO;
import com.tfe.service.PanierService;
import com.tfe.service.ParentService;
import com.tfe.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
//    private final TransactionService transactionService;
//    private final PanierService panierService;
//    private final ParentService parentService;
//
//    public TransactionController(TransactionService transactionService, PanierService panierService, ParentService parentService) {
//        this.transactionService = transactionService;
//        this.panierService = panierService;
//        this.parentService = parentService;
//    }
//    @GetMapping("/{id}")
//    public TransactionDTO getTransactionById(@PathVariable int id) {
//        return transactionService.getTransactionById(id);
//    }
//
//    @PostMapping
//    public TransactionDTO createTransaction(@Valid @RequestBody TransactionDTO dto) {
//        return transactionService.createTransaction(dto);
//    }
//
//    @PutMapping("/{id}/statut")
//    public ResponseEntity<Void> updateStatut(@PathVariable int id, @RequestBody TransactionDTO dto) {
//        transactionService.updateStatut(id, dto.getStatut());
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/open/{idParent}")
//    public TransactionDTO getOrCreateOpenTransaction(@PathVariable int idParent) {
//        return transactionService.getOrCreateOpenTransaction(idParent);
//    }
//    @GetMapping("/open/panier")
//    public PanierDTO getPanierForCurrentParent(@RequestAttribute("auth0UserId") String auth0UserId) {
//        int idParent = parentService.getIdParentByAuth0UserId(auth0UserId);
//        return panierService.getPanierByParentId(idParent);
//    }
}
