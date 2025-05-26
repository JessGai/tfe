package com.tfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tfe.entity.StageDescEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StageInstDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int idStageInst;

    @NotNull(message = "The price is required")
    @Positive(message = "The price must be positive")
    @JsonProperty("prix")
    private int prix;

    @NotNull(message = "The start date is required")
    @JsonProperty("dateDebut")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;

    @NotNull(message = "the number of participant is required")
    @Positive(message = "The number of participant must be positive")
    @JsonProperty("nbrParticipant")
    private int nbrParticipant;

    @JsonProperty("nbrInscrit")
    private int nbrInscrit;

    @NotNull(message = "The status is required")
    @JsonProperty("statut")
    private Boolean statut;

    @NotNull(message = "The stage description id is required")
    @JsonProperty("idStageDesc")
    private int idStageDesc;

    @JsonProperty("dateCreation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    @JsonProperty("dateUpdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateUpdate;

    //getter&setter
    public int getIdStageInst() {
        return idStageInst;
    }

    public void setIdStageInst(int idStageInst) {
        this.idStageInst = idStageInst;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getNbrParticipant() {
        return nbrParticipant;
    }

    public void setNbrParticipant(int nbrParticipant) {
        this.nbrParticipant = nbrParticipant;
    }

    public int getNbrInscrit() {
        return nbrInscrit;
    }

    public void setNbrInscrit(int nbrInscrit) {
        this.nbrInscrit = nbrInscrit;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public int getIdStageDesc() {
        return idStageDesc;
    }

    public void setIdStageDesc(int idStageDesc) {
        this.idStageDesc = idStageDesc;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

}
