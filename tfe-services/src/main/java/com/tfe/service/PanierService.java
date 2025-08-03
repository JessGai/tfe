package com.tfe.service;

import com.tfe.PanierRepository;
import com.tfe.dto.*;
import com.tfe.entity.PanierEntity;
import com.tfe.mapper.PanierMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PanierService {

private final StageDescService stageDescService;
private final StageInstService stageInstService;
private final EnfantService enfantService;

private final PanierRepository panierRepository;

private final PanierMapper mapper;

    public PanierService(StageDescService stageDescService, StageInstService stageInstService, EnfantService enfantService, PanierRepository panierRepository, PanierMapper mapper) {
        this.stageDescService = stageDescService;
        this.stageInstService = stageInstService;
        this.enfantService = enfantService;
        this.panierRepository = panierRepository;
        this.mapper = mapper;
    }

    public PanierFullDTO getPanierByParentId(int idParent) {

        List<PanierEntity> listePanier = panierRepository.findByParentIdParent(idParent);
        PanierFullDTO panierFullDTO = new PanierFullDTO();
        //panierFullDTO.setListe(new ArrayList<>());

        double montantTotal = 0;

        //convertir entité en dto
        for (PanierEntity entity : listePanier){
            PanierListForFullPanierDTO listForDto = new PanierListForFullPanierDTO();
            listForDto.setEnfantNom(entity.getEnfant().getNomEnfant());
            listForDto.setEnfantPrenom(entity.getEnfant().getPrenomEnfant());
            listForDto.setStageDescTheme(entity.getStageInstance().getStageDesc().getTheme());
            listForDto.setStageDescTitre(entity.getStageInstance().getStageDesc().getTitre());
            listForDto.setStageInstDateDebut(entity.getStageInstance().getDateDebut());
            listForDto.setStageInstDateFin(entity.getStageInstance().getDateDebut().plusDays(5));
            listForDto.setStageInstPrix(entity.getStageInstance().getPrix());
            montantTotal += entity.getStageInstance().getPrix();
            panierFullDTO.getListe().add(listForDto);
        }

        //calculer les montants
        int taillePanier = listePanier.size();
        double tauxReduction = calculerTauxReduction(taillePanier);
        double montantAvecReduction = montantTotal*(1-tauxReduction);
        panierFullDTO.setMontantTotal(montantTotal);
        panierFullDTO.setTauxReduction(tauxReduction);
        panierFullDTO.setMontantAvecReduction(montantAvecReduction);
        return panierFullDTO;
    }

    public double calculerTauxReduction(int nbInscriptions) {
        if (nbInscriptions == 2) {
            return 0.10;
        } else if (nbInscriptions >= 3) {
            return 0.20;
        } else {
            return 0.0;
        }
    }
    public PanierDTO savePanier(PanierDTO dto){
        PanierEntity entity = mapper.toEntity(dto);
        PanierEntity savedEntity = panierRepository.save(entity);
        return mapper.toDto(savedEntity);
    }
}
