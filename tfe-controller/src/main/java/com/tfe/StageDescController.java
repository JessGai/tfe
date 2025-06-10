package com.tfe;


import com.tfe.dto.StageDescDTO;
import com.tfe.dto.StageDescWithInstancesDTO;
import com.tfe.service.StageDescService;
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
    public StageDescDTO getById(@PathVariable int id){

        return stageService.getStageDescById(id);
    }

    @GetMapping
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
    public StageDescDTO save(@Valid @RequestBody StageDescDTO stage){
        return stageService.saveStageDesc(stage);
    }

    @GetMapping("/with-instances")
    public ResponseEntity<List<StageDescWithInstancesDTO>> getStageDescWithInstances() {
        try {
            List<StageDescWithInstancesDTO> result = stageService.getStageDescWithInstances();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
