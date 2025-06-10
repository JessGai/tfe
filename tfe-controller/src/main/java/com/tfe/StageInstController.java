package com.tfe;

import com.tfe.dto.StageForCardsDTO;
import com.tfe.dto.StageInstDto;
import com.tfe.service.StageInstService;
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
    public List<StageInstDto> getAllStageInstances() {
        return service.getAllStageInst();
    }

    @GetMapping("/{id}")
    public StageInstDto getById(@PathVariable int id) {
        return service.getStageInstById(id);
    }

    @PostMapping
    public StageInstDto save(@Valid @RequestBody StageInstDto stage) {
        return service.saveStageInst(stage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StageInstDto> updateStageInstance(@PathVariable int id, @RequestBody @Valid StageInstDto dto) {
        StageInstDto updated = service.updageStage(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StageInstDto> deleteStageInste(@PathVariable int id){
        service.deleteStageInstBtId(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/byTheme/{theme}")
    public ResponseEntity<List<StageInstDto>> getByTheme(@PathVariable String theme){
        return ResponseEntity.ok(service.getStageInstByTheme(theme));
    }
    @GetMapping("/cards")
    public List<StageForCardsDTO> getAllVisibleCards() {
        return service.getAllStageForCards();
    }
}