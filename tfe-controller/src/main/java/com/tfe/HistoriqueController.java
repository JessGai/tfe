package com.tfe;

import com.tfe.dto.HistoriqueDTO;
import com.tfe.service.HistoriqueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/historique")
public class HistoriqueController {
    private final HistoriqueService service;

    public HistoriqueController(HistoriqueService service) {
        this.service = service;
    }

    @GetMapping("/{idParent}")
    public List<HistoriqueDTO> getHistorique(@PathVariable int idParent) {
        return service.getHistorique(idParent);
    }
}
