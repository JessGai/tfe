package com.tfe;

import com.tfe.dto.EnfantDTO;
import com.tfe.service.EnfantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enfant")
public class EnfantController {
private final EnfantService service;

    public EnfantController(EnfantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public EnfantDTO getChildById(@PathVariable int id){
        return service.getEnfantById(id);
    }

    @PostMapping()
    public EnfantDTO createChild(@RequestBody EnfantDTO dto) {
        return service.createChild(dto);
    }

    @PutMapping("/{id}")
    public EnfantDTO editChild(@PathVariable int id, @RequestBody EnfantDTO dto){
        return  service.updateChild(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnfantDTO> deleteChild(@PathVariable int id){
        service.deleteChildById(id);
        return ResponseEntity.noContent().build();
    }
}
