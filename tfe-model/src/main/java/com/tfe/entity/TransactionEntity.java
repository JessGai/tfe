package com.tfe.entity;

import com.tfe.enums.TransactionStatut;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransaction;

    private double montant;
    private LocalDateTime dateTransaction;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatut statut;
    private String stripeSessionId;
    private String emailPayeur;
    @ManyToOne
    @JoinColumn(name = "fk_idParent", referencedColumnName = "idParent", nullable = false)
    private ParentEntity parent;
//    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
//    private List<InscriptionEntity> inscriptions;

    private double montantFinal;
    private double tauxReduction;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @Column(nullable = false)
    private LocalDateTime lastUpdate;



    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    //getters & setters

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public TransactionStatut getStatut() {
        return statut;
    }

    public void setStatut(TransactionStatut statut) {
        this.statut = statut;
    }

    public String getStripeSessionId() {
        return stripeSessionId;
    }

    public void setStripeSessionId(String stripeSessionId) {
        this.stripeSessionId = stripeSessionId;
    }

    public String getEmailPayeur() {
        return emailPayeur;
    }

    public void setEmailPayeur(String emailPayeur) {
        this.emailPayeur = emailPayeur;
    }

    public ParentEntity getParent() {
        return parent;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

//    public List<InscriptionEntity> getInscriptions() {
//        return inscriptions;
//    }
//
//    public void setInscriptions(List<InscriptionEntity> inscriptions) {
//        this.inscriptions = inscriptions;
//    }

    public double getMontantFinal() {
        return montantFinal;
    }

    public void setMontantFinal(double montantFinal) {
        this.montantFinal = montantFinal;
    }

    public double getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(double tauxReduction) {
        this.tauxReduction = tauxReduction;
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
