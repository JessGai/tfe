package com.tfe.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscription")
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscription;

    @ManyToOne
    @JoinColumn(name = "fk_idEnfant", referencedColumnName = "idEnfant", nullable = false)
    private EnfantEntity enfant;

    @ManyToOne
    @JoinColumn(name = "fk_idStageInstance", referencedColumnName = "id_stage_instance", nullable = false)
    private StageInstanceEntity stageInstance;

    @ManyToOne
    @JoinColumn(name = "fk_idTransaction", referencedColumnName = "idTransaction", nullable = true)
    private TransactionEntity transaction;

    private double montantEffectif;

    @Column(nullable = false)
    private LocalDateTime dateCreation;
    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    //remplissage de la date la 1ere fois
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    //repmlissage uniquement si update
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    //getter & setters


    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }

    public EnfantEntity getEnfant() {
        return enfant;
    }

    public void setEnfant(EnfantEntity enfant) {
        this.enfant = enfant;
    }

    public StageInstanceEntity getStageInstance() {
        return stageInstance;
    }

    public void setStageInstance(StageInstanceEntity stageInstance) {
        this.stageInstance = stageInstance;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public double getMontantEffectif() {
        return montantEffectif;
    }

    public void setMontantEffectif(double montantEffectif) {
        this.montantEffectif = montantEffectif;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
