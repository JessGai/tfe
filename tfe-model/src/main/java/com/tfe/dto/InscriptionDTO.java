package com.tfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tfe.enums.TransactionStatut;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class InscriptionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private int idInscription;
    @NotNull(message="The childId is required")
    private int idEnfant;
    @NotNull(message="The stageInstancedId is required")
    private int idStageInstance ;
     private TransactionStatut statut;
     private double tauxReduction;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastUpdate;

    //getter & setters

    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }

    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public int getIdStageInstance() {
        return idStageInstance;
    }

    public void setIdStageInstance(int idStageInstance) {
        this.idStageInstance = idStageInstance;
    }

    public TransactionStatut getStatut() {
        return statut;
    }

    public void setStatut(TransactionStatut statut) {
        this.statut = statut;
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
