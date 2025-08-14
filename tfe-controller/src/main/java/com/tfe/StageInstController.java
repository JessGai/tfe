package com.tfe;

import com.tfe.dto.StageForCardsDTO;
import com.tfe.dto.StageInstDto;
import com.tfe.service.StageInstService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stageinst")
public class StageInstController {

    private final StageInstService service;

    public StageInstController(StageInstService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Renvoie tout les instances de stage",
            tags = {"Stages"}
    )
    public List<StageInstDto> getAllStageInstances() {
        return service.getAllStageInst();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Renvoie une instance de stage selon son id",
            tags = {"Stages"}
    )
    public StageInstDto getById(@PathVariable int id) {
        return service.getStageInstById(id);
    }

    @PostMapping
    @Operation(
            summary = "Création d'une instance de stage",
            tags = {"Stages"}
    )
    public StageInstDto save(@Valid @RequestBody StageInstDto stage) {
        return service.saveStageInst(stage);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Modification d'une instance de stage",
            tags = {"Stages"}
    )
    public StageInstDto updateStageInstance(@PathVariable int id, @RequestBody @Valid StageInstDto dto) {
        return service.updageStage(id, dto);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Suppression d'une instance de stage selon son id, une vérification est faite sur le nombre d'inscrit (si >0 alors suppression impossible)",
            tags = {"Stages"}
    )
    public ResponseEntity<StageInstDto> deleteStageInste(@PathVariable int id){
        service.deleteStageInstBtId(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/byTheme/{theme}")
    @Operation(
            summary = "Renvoie une instance de stage selon son theme",
            tags = {"Stages"}
    )
    public List<StageInstDto> getByTheme(@PathVariable String theme){
        return service.getStageInstByTheme(theme);
    }


    @GetMapping("/cards")
    @Operation(
            summary = "Renvoi la liste des stages Descriptions avec ses instances ayant un statut actif",
            tags = {"Stages"}

    )
    public List<StageForCardsDTO> getAllVisibleCards() {
        return service.getAllStageForCards();
    }
}