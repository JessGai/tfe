package com.tfe;

import com.tfe.dto.PanierDTO;
import com.tfe.dto.PanierFullDTO;
import com.tfe.dto.StageInstDto;
import com.tfe.dto.TransactionDTO;
import com.tfe.service.PanierService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
public class PanierController {
    private PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }


    @GetMapping("/{idParent}")
    @Operation(
            summary = "Envoi les données pour l'affichage du panier côté front",
            description = "",
            tags = {"Panier"}

    )
    public PanierFullDTO getPanierByIdParent(@PathVariable int idParent) {

        return panierService.getPanierByParentId(idParent);
    }

    @PostMapping()
    @Operation(
            summary = "Ajout d'une inscription dans le panier",
            tags = {"Panier"}
    )
    public void save(@Valid @RequestBody PanierDTO dto) {
        panierService.savePanier(dto);
    }
}
