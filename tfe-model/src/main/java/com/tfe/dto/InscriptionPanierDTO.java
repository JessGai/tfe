package com.tfe.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class InscriptionPanierDTO {
    @NotNull(message = "The Firstname is required")
    private String prenomEnfant;
    @NotNull(message = "The name is required")
    private String nomEnfant;
    @NotNull(message = "The title is required")
    private String titreStage;
    @NotNull(message = "The theme is required")
    private String theme;
    @NotNull(message = "The start date is required")
    private LocalDate dateDebut;
    @NotNull(message = "The end date is required")
    private LocalDate dateFin;
    @NotNull(message = "The price is required")
    private double prixOriginal;
    @NotNull(message = "The price after the reduction is required")
    private double prixApresReduction;
    private int idInscription;

    //getter & setter

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getTitreStage() {
        return titreStage;
    }

    public void setTitreStage(String titreStage) {
        this.titreStage = titreStage;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrixOriginal() {
        return prixOriginal;
    }

    public void setPrixOriginal(double prixOriginal) {
        this.prixOriginal = prixOriginal;
    }

    public double getPrixApresReduction() {
        return prixApresReduction;
    }

    public void setPrixApresReduction(double prixApresReduction) {
        this.prixApresReduction = prixApresReduction;
    }

    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }
}
