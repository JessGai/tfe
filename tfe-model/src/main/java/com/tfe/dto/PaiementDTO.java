package com.tfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.tfe.enums.TransactionStatut;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class PaiementDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int idPaiement;

    private double montant;

    private TransactionStatut statut;

    private int idParent;

    private String idStripe;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastUpdate;

    //getter & setters

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

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
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
