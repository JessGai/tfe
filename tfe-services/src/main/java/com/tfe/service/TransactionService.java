package com.tfe.service;

import com.tfe.ParentRepository;
import com.tfe.TransactionRepository;
import com.tfe.dto.TransactionDTO;
import com.tfe.entity.InscriptionEntity;
import com.tfe.entity.ParentEntity;
import com.tfe.entity.TransactionEntity;
import com.tfe.enums.TransactionStatut;
import com.tfe.exception.ParentNotFoundException;
import com.tfe.exception.TransactionNotFoundException;
import com.tfe.mapper.TransactionMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

//    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
//
//    private final TransactionRepository transactionRepository;
//    private final TransactionMapper transactionMapper;
//    private final ParentRepository parentRepository;
//
//    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, ParentRepository parentRepository) {
//        this.transactionRepository = transactionRepository;
//        this.transactionMapper = transactionMapper;
//        this.parentRepository = parentRepository;
//    }
//
//    //méthode pour soit créer une transaction soit récupérer la dernière avec statut !payé pour le parent connecté
//    public TransactionDTO getOrCreateOpenTransaction(int idParent) {
//        logger.info("Recherche ou création d'une transaction ouverte pour le parent {}", idParent);
//
//        // Cherche une transaction existante ouverte
//        Optional<TransactionEntity> existing = transactionRepository
//                .findFirstByParent_IdParentAndStatutOrderByDateCreationDesc(idParent, TransactionStatut.EN_ATTENTE);
//
//        if (existing.isPresent()) {
//            logger.info("Transaction ouverte existante trouvée : {}", existing.get().getIdTransaction());
//            return transactionMapper.toDto(existing.get());
//        }
//
//        // Récupère le parent
//        ParentEntity parent = parentRepository.findById(idParent)
//                .orElseThrow(() -> new RuntimeException("Parent non trouvé avec l'id " + idParent));
//
//        // Crée une nouvelle transaction
//        TransactionEntity entity = new TransactionEntity();
//        entity.setParent(parent);
//        entity.setMontant(0.0);
//        entity.setMontantFinal(0.0);
//        entity.setTauxReduction(0);
//        entity.setStatut(TransactionStatut.EN_ATTENTE);
//        entity.setEmailPayeur(parent.getEmail());
//        entity.setDateTransaction(LocalDateTime.now());
//
//        TransactionEntity saved = transactionRepository.save(entity);
//        logger.info("Nouvelle transaction créée avec l'id {}", saved.getIdTransaction());
//
//        return transactionMapper.toDto(saved);
//    }
//    public TransactionDTO createTransaction(TransactionDTO dto) {
//        logger.info("Création d'une nouvelle transaction pour le parent avec ID: {}", dto.getIdParent());
//
//        ParentEntity parent = parentRepository.findById(dto.getIdParent())
//                .orElseThrow(() -> {
//                    logger.error("Parent avec ID {} non trouvé", dto.getIdParent());
//                    return new ParentNotFoundException(dto.getIdParent());
//                });
//
//        TransactionEntity entity = transactionMapper.toEntity(dto);
//        entity.setParent(parent);
//        entity.setStatut(TransactionStatut.EN_ATTENTE);
//
//        TransactionEntity saved = transactionRepository.save(entity);
//        logger.info("Transaction créée avec succès (ID: {})", saved.getIdTransaction());
//
//        return transactionMapper.toDto(saved);
//    }
//
//    public TransactionDTO getTransactionById(int id) {
//        logger.info("Récupération de la transaction avec ID: {}", id);
//
//        TransactionEntity entity = transactionRepository.findById(id)
//                .orElseThrow(() -> {
//                    logger.warn("Transaction avec ID {} non trouvée", id);
//                    return new TransactionNotFoundException(id);
//                });
//
//        logger.info("Transaction récupérée avec succès");
//        return transactionMapper.toDto(entity);
//    }
//
//    public void updateStatut(int idTransaction, TransactionStatut newStatut) {
//        logger.info("Mise à jour du statut de la transaction ID {} vers '{}'", idTransaction, newStatut);
//
//        TransactionEntity entity = transactionRepository.findById(idTransaction)
//                .orElseThrow(() -> {
//                    logger.warn("Transaction avec ID {} non trouvée", idTransaction);
//                    return new TransactionNotFoundException(idTransaction);
//                });
//
//        entity.setStatut(newStatut);
//
//        transactionRepository.save(entity);
//        logger.info("Statut de la transaction ID {} mis à jour avec succès", idTransaction);
//    }
//
//    public void updateMontantsAndReduction(TransactionEntity transaction) {
//        //pas d'inscription pour la transaction en cours création d'une ligne "vide":
//        if (transaction.getInscriptions() == null || transaction.getInscriptions().isEmpty()) {
//            transaction.setMontant(0.0);
//            transaction.setTauxReduction(0.0);
//            transaction.setMontantFinal(0.0);
//            return;
//        }
//
//        //si inscription avec fk_transaction
//
//        //je récupère le montant de l'instance qui est dans l'inscription
//        double montant = transaction.getInscriptions().stream()
//                .mapToDouble(ins -> ins.getStageInstance().getPrix())
//                .sum();
//
//        //je vérifie le nombre d'inscription pour la transaction en cours:
//        int nbInscriptions = transaction.getInscriptions().size();
//
//        //je récupere la methode du calcul
//        double tauxReduction = calculerTauxReduction(nbInscriptions);
//
//        //calcul du montantFinal avec réduction - si pas de reduction montant*1
//        double montantFinal = montant * (1 - tauxReduction);
//
//        //j'ajoute le nouveau montant total sans reduction
//        transaction.setMontant(montant);
//
//        //j'ajoute le taux de reduction
//        transaction.setTauxReduction(tauxReduction);
//
//        //j'ajoute le nouveau montant total apres reduction
//        transaction.setMontantFinal(montantFinal);
//    }
//
//    public void setMontantTotal(TransactionEntity transaction) {
//        //je récupère le montant de l'instance qui est dans l'inscription
//        double montant = transaction.getInscriptions().stream()
//                .mapToDouble(ins -> ins.getStageInstance().getPrix())
//                .sum();
//
//        double montantEffectif = transaction.getInscriptions().stream().mapToDouble(InscriptionEntity::getMontantEffectif).sum();
//
//        transaction.setMontant(montant);
//        transaction.setMontantFinal(montantEffectif);
//    }
//
//    //calcul des réductions
//    public double calculerTauxReduction(int nbInscriptions) {
//        if (nbInscriptions == 2) {
//            return 0.10;
//        } else if (nbInscriptions >= 3) {
//            return 0.20;
//        } else {
//            return 0.0;
//        }
//    }
//
//    public void addInscriptionToTransaction(InscriptionEntity inscription, TransactionEntity transaction) {
//        transaction.getInscriptions().add(inscription);
//        if (transaction.getInscriptions().size() == 2) {
//            addReductionToTransaction(0.1, transaction);
//        } else if (transaction.getInscriptions().size() == 3) {
//            addReductionToTransaction(0.1, transaction);
//        }
//        for (InscriptionEntity i : transaction.getInscriptions() ){
//            i.setMontantEffectif(i.getStageInstance().getPrix() * (1 - transaction.getTauxReduction()));
//        }
//    }
//
//    private void addReductionToTransaction(Double reduction, TransactionEntity transaction) {
//        transaction.setTauxReduction(transaction.getTauxReduction() + reduction);
//    }

}
