package com.tfe;

import com.tfe.dto.InscriptionDTO;
import com.tfe.service.InscriptionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscription")
public class InscriptionController {

//    private final InscriptionService inscriptionService;
//
//    public InscriptionController(InscriptionService inscriptionService) {
//        this.inscriptionService = inscriptionService;
//    }
//
////    @PostMapping
////    public InscriptionDTO save(@Valid @RequestBody InscriptionDTO dto) {
////        return inscriptionService.addInscriptionToTransaction(dto);
////    }
}
