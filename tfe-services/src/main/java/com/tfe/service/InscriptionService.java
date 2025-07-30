package com.tfe.service;

import com.tfe.EnfantRepository;
import com.tfe.InscriptionRepository;
import com.tfe.StageInstanceRepository;
import com.tfe.TransactionRepository;
import com.tfe.dto.InscriptionDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.InscriptionEntity;
import com.tfe.entity.StageInstanceEntity;
import com.tfe.entity.TransactionEntity;
import com.tfe.exception.ChildNotFoundException;
import com.tfe.exception.StageNotFoundException;
import com.tfe.exception.TransactionNotFoundException;
import com.tfe.mapper.InscriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InscriptionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final InscriptionRepository inscriptionRepository;
    private final InscriptionMapper inscriptionMapper;
    private final EnfantRepository enfantRepository;
    private final StageInstanceRepository stageInstanceRepository;
    private final TransactionRepository transactionRepository;

    private final TransactionService transactionService;

    public InscriptionService(InscriptionRepository inscriptionRepository, InscriptionMapper inscriptionMapper, EnfantRepository enfantRepository, StageInstanceRepository stageInstanceRepository, TransactionRepository transactionRepository, TransactionService transactionService) {
        this.inscriptionRepository = inscriptionRepository;
        this.inscriptionMapper = inscriptionMapper;
        this.enfantRepository = enfantRepository;
        this.stageInstanceRepository = stageInstanceRepository;
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }


    public InscriptionDTO addInscription(InscriptionDTO dto) {

        //vérification que tout existe
        EnfantEntity enfant = enfantRepository.findById(dto.getIdEnfant())
                .orElseThrow(() -> new ChildNotFoundException(dto.getIdEnfant()));

        StageInstanceEntity stage = stageInstanceRepository.findById(dto.getIdStageInstance())
                .orElseThrow(() -> new StageNotFoundException(dto.getIdStageInstance()));

        TransactionEntity transaction = transactionRepository.findById(dto.getIdTransaction())
                .orElseThrow(() -> new TransactionNotFoundException(dto.getIdTransaction()));

        //je récupère le prix de l'instance du stage
        double prixInstance = stage.getPrix();

        //création de l'inscription
        InscriptionEntity entity = new InscriptionEntity();
        entity.setEnfant(enfant);
        entity.setStageInstance(stage);
        entity.setTransaction(transaction);
        entity.setMontantEffectif(prixInstance);

        InscriptionEntity saved = inscriptionRepository.save(entity);

        // Calul des montants lors de l'ajout d'une inscription par le parent
        transaction.getInscriptions().add(saved);
        transactionService.updateMontantsAndReduction(transaction);

        transactionRepository.save(transaction);

        return inscriptionMapper.toDto(saved);
    }


}
