package com.tfe.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistoriqueDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String titreStage;
    private String themeStage;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String prenomEnfant;
    private String nomEnfant;
    private double prixApresReduction;
    private double tauxReduction;
    private LocalDateTime datePaiement;

    public HistoriqueDTO(String titreStage, String themeStage, LocalDate dateDebut, LocalDate dateFin,
                                    String prenomEnfant, String nomEnfant, double prixApresReduction,
                                    double tauxReduction, LocalDateTime datePaiement) {
        this.titreStage = titreStage;
        this.themeStage = themeStage;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prenomEnfant = prenomEnfant;
        this.nomEnfant = nomEnfant;
        this.prixApresReduction = prixApresReduction;
        this.tauxReduction = tauxReduction;
        this.datePaiement = datePaiement;
    }
    //getter & setters

    public String getTitreStage() {
        return titreStage;
    }

    public void setTitreStage(String titreStage) {
        this.titreStage = titreStage;
    }

    public String getThemeStage() {
        return themeStage;
    }

    public void setThemeStage(String themeStage) {
        this.themeStage = themeStage;
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

    public double getPrixApresReduction() {
        return prixApresReduction;
    }

    public void setPrixApresReduction(double prixApresReduction) {
        this.prixApresReduction = prixApresReduction;
    }

    public double getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(double tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }
}
