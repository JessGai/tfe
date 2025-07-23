package com.tfe;

import com.tfe.dto.ParentDTO;
import com.tfe.dto.ParentWithChildrenDTO;
import com.tfe.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;

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
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public void getMessageAdmin(){
        System.out.println("message admin");
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
    public ResponseEntity<ParentWithChildrenDTO> getMyData(@AuthenticationPrincipal Jwt principal) {
        // Récupérer l'identifiant unique Auth0 du token JWT
        String auth0UserId = principal.getSubject();
        ParentWithChildrenDTO dto = service.getParentWithChildren(auth0UserId);
        return ResponseEntity.ok(dto);
    }
//    @GetMapping("/me")
//    public ParentDTO getCurrentParent(@RequestAttribute("auth0UserId") String auth0UserId) {
//        return service.getParentByAuth0UserId(auth0UserId);
//    }

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
