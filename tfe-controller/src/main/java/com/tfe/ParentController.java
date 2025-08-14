package com.tfe;

import com.tfe.dto.ParentDTO;
import com.tfe.dto.ParentWithChildrenDTO;
import com.tfe.service.ParentService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.List;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;

@RestController
@RequestMapping("/api/parent")
public class ParentController {
    private static final Logger logger = LoggerFactory.getLogger(ParentService.class);
    private final ParentService service;

    public ParentController(ParentService service){
        this.service=service;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Renvoi un parent selon son id",
            tags = {"Parent"}

    )
    public ParentDTO getParentById(@PathVariable int id){

        return service.getParentById(id);
    }


    @GetMapping
    @Operation(
            summary = "Renvoi la liste des parents",
            tags = {"Parent"}

    )
    public List<ParentDTO> getAllParents(){
        return service.getAllParents();
    }

    @PostMapping
    @Operation(
            summary = "Permet la création d'un parent (non utilisé coté front)",
            tags = {"Parent"}

    )

    public ParentDTO createParent(@RequestBody ParentDTO dto, @AuthenticationPrincipal Jwt principal) {
        String auth0UserId = principal.getSubject();
        return service.createParent(dto, auth0UserId);
    }
    @PostMapping("/me")
    @Operation(
            summary = "Permet la création d'un parent",
            tags = {"Parent"}

    )
    public ParentDTO createParentForCurrentUser(@RequestBody ParentDTO dto, @RequestAttribute("auth0UserId") String auth0UserId) {
        return service.createParent(dto, auth0UserId);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Récupère les données du parent connecté y compris ses enfants)",
            tags = {"Parent"}

    )
    public ParentWithChildrenDTO getMyData2(@AuthenticationPrincipal Jwt principal) {
        // Récupérer l'identifiant unique Auth0 du token JWT
        String auth0UserId = principal.getSubject();
        return service.getParentWithChildren(auth0UserId);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Permet la modification des données d'un parent (non utilisé coté front)",
            tags = {"Parent"}

    )
    public ParentDTO updateParent(@PathVariable int id, @RequestBody ParentDTO dto) {
        return service.updateParent(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprime un parent (non utilisé coté front). Ne peut être supprimé si un enfant est lié au parent",
            tags = {"Parent"}

    )
    public ResponseEntity<ParentDTO> deleteParent(@PathVariable int id){
        service.deleteParentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists")
    @Operation(
            summary = "Permet de savoir si le parent existe",
            tags = {"Parent"}

    )
    public Boolean parentExists(@RequestParam String auth0UserId) {
        boolean exists = service.existsByAuth0UserId(auth0UserId);
        logger.info("Parent exists = {}", exists);
        return exists;
    }
}
