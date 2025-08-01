package com.tfe.service;

import com.tfe.InscriptionRepository;
import com.tfe.TransactionRepository;
import com.tfe.dto.InscriptionPanierDTO;
import com.tfe.dto.PanierDTO;
import com.tfe.entity.InscriptionEntity;
import com.tfe.entity.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {

    private final TransactionRepository transactionRepository;
    private final InscriptionRepository inscriptionRepository;

    public PanierService(TransactionRepository transactionRepository, InscriptionRepository inscriptionRepository) {
        this.transactionRepository = transactionRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    public PanierDTO getPanierByParentId(int idParent) {

        //je récupère la transction du parent
        TransactionEntity transaction = transactionRepository.findByIdParentAndStatut(idParent, "EN_ATTENTE")
                .orElseThrow(() -> new RuntimeException("Pas de transaction ouverte pour ce parent"));
        //je récupère la liste des inscription pour la transaction
        List<InscriptionEntity> inscriptions = inscriptionRepository.findByTransaction_IdTransaction(transaction.getIdTransaction());
        //je cree les données pour le panier
        List<InscriptionPanierDTO> lignes = inscriptions.stream().map(inscription -> {
            InscriptionPanierDTO dto = new InscriptionPanierDTO();
            dto.setIdInscription(inscription.getIdInscription());
            dto.setPrenomEnfant(inscription.getEnfant().getPrenomEnfant());
            dto.setNomEnfant(inscription.getEnfant().getNomEnfant());
            dto.setTitreStage(inscription.getStageInstance().getStageDesc().getTitre());
            dto.setTheme(inscription.getStageInstance().getStageDesc().getTheme());
            dto.setDateDebut(inscription.getStageInstance().getDateDebut());
            dto.setDateFin(inscription.getStageInstance().getDateDebut().plusDays(5));
            dto.setPrixOriginal(inscription.getMontantEffectif());
            dto.setPrixApresReduction(inscription.getMontantEffectif());
            return dto;
        }).toList();

        PanierDTO panier = new PanierDTO();
        panier.setIdTransaction(transaction.getIdTransaction());
        panier.setMontant(transaction.getMontant());
        panier.setMontantFinal(transaction.getMontantFinal());
        panier.setTauxReduction(transaction.getTauxReduction());
        panier.setInscriptions(lignes);

        return panier;
    }
}
