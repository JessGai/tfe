package com.tfe.entity;

import com.tfe.enums.TransactionStatut;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "paiement")
public class PaiementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaiement;

    private double montant;
    @Enumerated(EnumType.STRING)
    private TransactionStatut statut;
    @ManyToOne
    @JoinColumn(name = "fk_idParent", referencedColumnName = "idParent", nullable = false)
    private ParentEntity parent;
    @Column(nullable = false, unique = true)
    private String idStripe;
    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    // getters & setters

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public TransactionStatut getStatut() {
        return statut;
    }

    public void setStatut(TransactionStatut statut) {
        this.statut = statut;
    }

    public ParentEntity getParent() {
        return parent;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

    public String getIdStripe() {
        return idStripe;
    }

    public void setIdStripe(String idStripe) {
        this.idStripe = idStripe;
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
