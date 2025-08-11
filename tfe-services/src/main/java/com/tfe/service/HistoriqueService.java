package com.tfe.service;

import com.tfe.HistoriqueRepository;
import com.tfe.dto.HistoriqueDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueService {

    private final HistoriqueRepository repository;

    public HistoriqueService(HistoriqueRepository repository) {
        this.repository = repository;
    }

    public List<HistoriqueDTO> getHistorique(int idParent) {
        return repository.findHistoriqueByParentId(idParent);
    }
}
