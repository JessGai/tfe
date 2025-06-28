package com.tfe;

import com.tfe.dto.ParentDTO;
import com.tfe.dto.StageDescDTO;
import com.tfe.dto.StageInstDto;
import com.tfe.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parent")
public class ParentController {

    private final ParentService service;

    public ParentController(ParentService service){
        this.service=service;
    }
    @GetMapping("/{id}")
    public ParentDTO getParentById(@PathVariable int id){

        return service.getParentById(id);
    }

    @GetMapping
    public List<ParentDTO> getAllParents(){
        return service.getAllParents();
    }

    @PostMapping("/me")
    public ParentDTO createParentForCurrentUser(@RequestBody ParentDTO dto, @RequestAttribute("auth0UserId") String auth0UserId) {
        return service.createParent(dto, auth0UserId);
    }

    @GetMapping("/me")
    public ParentDTO getCurrentParent(@RequestAttribute("auth0UserId") String auth0UserId) {
        return service.getParentByAuth0UserId(auth0UserId);
    }

    @PutMapping("/{id}")
    public ParentDTO updateParent(@PathVariable int id, @RequestBody ParentDTO dto) {
        return service.updateParent(id, dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ParentDTO> deleteParent(@PathVariable int id){
        service.deleteParentById(id);
        return ResponseEntity.noContent().build();
    }
}
