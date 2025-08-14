package com.tfe.service;

import com.tfe.InscriptionRepository;
import com.tfe.PanierRepository;
import com.tfe.dto.*;
import com.tfe.entity.PanierEntity;
import com.tfe.mapper.PanierMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PanierService {

private final StageDescService stageDescService;
private final StageInstService stageInstService;
private final EnfantService enfantService;

private final PanierRepository panierRepository;
private final InscriptionRepository inscriptionRepository;

private final PanierMapper mapper;

    public PanierService(StageDescService stageDescService, StageInstService stageInstService, EnfantService enfantService, PanierRepository panierRepository, PanierMapper mapper, InscriptionRepository inscriptionRepository) {
        this.stageDescService = stageDescService;
        this.stageInstService = stageInstService;
        this.enfantService = enfantService;
        this.panierRepository = panierRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.mapper = mapper;
    }

    public PanierFullDTO getPanierByParentId(int idParent) {

        List<PanierEntity> listePanier = panierRepository.findByParentIdParent(idParent);
        PanierFullDTO panierFullDTO = new PanierFullDTO();
        double montantTotal = 0;
        int taillePanier = listePanier.size();
        double tauxReduction = calculerTauxReduction(taillePanier);
        double montantAvecReduction = 0;


        //convertir entité en dto
        for (PanierEntity entity : listePanier){
            PanierListForFullPanierDTO listForDto = new PanierListForFullPanierDTO();
            listForDto.setEnfantNom(entity.getEnfant().getNomEnfant());
            listForDto.setEnfantPrenom(entity.getEnfant().getPrenomEnfant());
            listForDto.setStageDescTheme(entity.getStageInstance().getStageDesc().getTheme());
            listForDto.setStageDescTitre(entity.getStageInstance().getStageDesc().getTitre());
            listForDto.setStageInstDateDebut(entity.getStageInstance().getDateDebut());
            listForDto.setStageInstDateFin(entity.getStageInstance().getDateDebut().plusDays(5));
            listForDto.setStageInstPrix((entity.getStageInstance().getPrix())*(1-tauxReduction));
            montantTotal += entity.getStageInstance().getPrix();
            montantAvecReduction += listForDto.getStageInstPrix();
            panierFullDTO.getListe().add(listForDto);
        }

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


        int idEnfant = dto.getIdEnfant();
        int idStageInst = dto.getIdStageInstance();

        //verif enfant deja inscrit dans le panier pour le stage choisit
        if(panierRepository.existsByEnfantIdEnfantAndStageInstanceIdStageInst(idEnfant, idStageInst)){
            throw new IllegalStateException("L'enfant est déjà inscrit a ce stage dans le panier");
        }

        StageInstDto stageInstance = stageInstService.getStageInstById(idStageInst);
        LocalDate dateDebut = stageInstService.getStageInstById(idStageInst).getDateDebut();
        //verif par rapport aux inscriptions deja payées:
        if(inscriptionRepository.existsByEnfantIdEnfantAndStageInstanceDateDebut(idEnfant, dateDebut)){
            throw new IllegalStateException("L'enfant est déjà insrcrit a un stage à la même date");
        }

        EnfantDTO enfant = enfantService.getEnfantById(idEnfant);
        StageDescDTO stageDesc = stageDescService.getStageDescById(stageInstance.getIdStageDesc());

        //vérif de l'age de l'enfant pour le stage
        int age = calculerAge(enfant.getDateNaissance(), dateDebut);
        if (age < stageDesc.getAgeMin() || age > stageDesc.getAgeMax()) {
            throw new IllegalArgumentException("L’enfant n’a pas l’âge requis pour ce stage.");
        }

        //! pas oublie la verif panier <> meme date <>autre stage
        List<PanierEntity> panierEnfant = panierRepository.findByEnfantIdEnfant(idEnfant);

        for (PanierEntity p : panierEnfant){
            if(p.getStageInstance().getDateDebut().equals(dateDebut)){
                throw new IllegalStateException("L'enfant est déjà lié a un stage à la même date dans le panier");
            }
        }

        //verif que le stage n'est pas complet lors du paiement
        if(stageInstance.getNbrInscrit() >= stageInstance.getNbrParticipant()){
            throw new IllegalStateException("Le stage est malheureusement complet");
        }

        PanierEntity entity = mapper.toEntity(dto);
        PanierEntity savedEntity = panierRepository.save(entity);
        return mapper.toDto(savedEntity);
    }

    private int calculerAge(LocalDate dateNaissance, LocalDate reference) {
        return Period.between(dateNaissance, reference).getYears();
    }
}
