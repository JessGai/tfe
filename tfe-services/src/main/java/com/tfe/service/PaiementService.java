package com.tfe.service;

import com.stripe.model.PaymentIntent;
import com.tfe.InscriptionRepository;
import com.tfe.PaiementRepository;
import com.tfe.PanierRepository;
import com.tfe.ParentRepository;
import com.tfe.entity.*;
import com.tfe.enums.TransactionStatut;
import com.tfe.exception.ParentNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaiementService {
    private final InscriptionRepository inscriptionRepository;
    private final PanierRepository panierRepository;
    private final StageInstService stageInstService;
    private final ParentRepository parentRepository;
    private final PaiementRepository paiementRepository;

    public PaiementService(InscriptionRepository inscriptionRepository,
                           PanierRepository panierRepository,
                           StageInstService stageInstService, ParentRepository parentRepository, PaiementRepository paiementRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.panierRepository = panierRepository;
        this.stageInstService = stageInstService;
        this.parentRepository = parentRepository;
        this.paiementRepository = paiementRepository;
    }

    @Transactional
    public void gestionPaiementReussi(String idStripe, int idParent, double montant) {
        // 3. Récupérer les éléments du panier pour ce parent
        // Ici tu devrais avoir la logique pour trouver le panier lié à ce paiement
        // Ex:
        List<PanierEntity> panierItems = panierRepository.findByParentIdParent(idParent);
        if (panierItems.isEmpty()) {
            System.out.println("Panier vide pour parent " + idParent);
            return;
        }
        double tauxRed = calculerTauxReduction(panierItems.size());
        // 4. Pour chaque élément du panier, créer une inscription (enregistrée en base)
        // Par exemple:
        for (PanierEntity panierItem : panierItems) {
            InscriptionEntity inscription = new InscriptionEntity();
            inscription.setEnfant(panierItem.getEnfant());
            inscription.setStageInstance(panierItem.getStageInstance());
            inscription.setDateCreation(LocalDateTime.now());
            inscription.setStatut(TransactionStatut.PAYE);
            inscription.setTauxReduction(tauxRed);
            inscriptionRepository.save(inscription);
        }

        // 5. Mettre à jour les effectifs du stage instance (nbr inscrit +1 par inscription)
        // Par exemple :
        for (PanierEntity panierItem : panierItems) {
            StageInstanceEntity stageInstance = panierItem.getStageInstance();
            stageInstance.setNbrInscrit(stageInstance.getNbrInscrit() + 1);
            stageInstService.updateStatut(stageInstance); // ou repository.save
        }

        // Enrigistrer dans table paiement
        ParentEntity par = parentRepository.findById(idParent)
                .orElseThrow(() -> new ParentNotFoundException(idParent));
        PaiementEntity paiement = new PaiementEntity();
        paiement.setMontant(montant);
        paiement.setStatut(TransactionStatut.PAYE);
        paiement.setIdStripe(idStripe);
        paiement.setDateCreation(LocalDateTime.now());
        paiement.setLastUpdate(LocalDateTime.now());
        paiement.setParent(par);

        paiementRepository.save(paiement);
        // 6. Vider le panier du parent (supprimer les entrées)
        ParentEntity parent = parentRepository.findById(idParent)
                .orElseThrow(() -> new ParentNotFoundException(idParent));
        panierRepository.deleteByParent(parent);


        // 7. Eventuellement log ou envoyer notification

        System.out.println("Payment succeeded, traitement terminé pour paymentId: " + idParent);
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
}
