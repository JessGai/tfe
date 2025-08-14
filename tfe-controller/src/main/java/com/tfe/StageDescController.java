package com.tfe;


import com.tfe.dto.StageDescDTO;
import com.tfe.dto.StageDescWithInstancesDTO;
import com.tfe.service.StageDescService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stagedesc")
public class StageDescController {
    private final StageDescService stageService;

    public StageDescController(StageDescService service) {
        this.stageService = service;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Récupère une description de stage selon son id",
            tags = {"Stage"}

    )
    public StageDescDTO getById(@PathVariable int id){

        return stageService.getStageDescById(id);
    }

    @GetMapping
    @Operation(
            summary = "Récupère toutes les descriptions de stage, permet la pagination d'un tableau",
            tags = {"Stage"}

    )
    public ResponseEntity<?> getAllStageDesc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
         Page<StageDescDTO> result = stageService.getAllStageDesc(page, size);
         if(result.isEmpty()){
             return ResponseEntity
                     .status(HttpStatus.OK)
                     .body("No items");
         }
         return ResponseEntity.ok(result);

    }
    @PostMapping
    @Operation(
            summary = "Création une description de stage selon son id",
            tags = {"Stage"}

    )
    public StageDescDTO save(@Valid @RequestBody StageDescDTO stage){
        return stageService.saveStageDesc(stage);
    }

    @GetMapping("/with-instances")
    @Operation(
            summary = "Envoi la liste des stageDescription avec ses instances",
            tags = {"Stage"}
    )
    public List<StageDescWithInstancesDTO> getStageDescWithInstances() {

        return stageService.getStageDescWithInstances();

    }
}
