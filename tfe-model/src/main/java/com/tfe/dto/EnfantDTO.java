package com.tfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EnfantDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int idEnfant;
    private int idParent;
    @NotNull(message = "The name is required")
    @JsonProperty("nomEnfant")
    private String nomEnfant;
    @NotNull(message = "The surname is required")
    @JsonProperty("prenomEnfant")
    private String prenomEnfant;
    @NotNull(message = "The birthdate is required")
    @JsonProperty("dateNaissance")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;

    @JsonProperty("commentaire")
    private String commentaire;

    @JsonProperty("dateCreation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;
    @JsonProperty("lastUpdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastUpdate;

    //getter & setter

    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

