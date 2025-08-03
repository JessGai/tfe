package com.tfe.dto;

import java.io.Serial;
import java.time.LocalDate;

public class PanierListForFullPanierDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    private String stageDescTitre;
    private String stageDescTheme;
    private LocalDate stageInstDateDebut;

    private LocalDate stageInstDateFin;
    private double stageInstPrix;
    private String enfantPrenom;
    private String enfantNom;

    //getter and setters

    public String getStageDescTitre() {
        return stageDescTitre;
    }

    public void setStageDescTitre(String stageDescTitre) {
        this.stageDescTitre = stageDescTitre;
    }

    public String getStageDescTheme() {
        return stageDescTheme;
    }

    public void setStageDescTheme(String stageDescTheme) {
        this.stageDescTheme = stageDescTheme;
    }

    public LocalDate getStageInstDateDebut() {
        return stageInstDateDebut;
    }

    public void setStageInstDateDebut(LocalDate stageInstDateDebut) {
        this.stageInstDateDebut = stageInstDateDebut;
    }

    public LocalDate getStageInstDateFin() {
        return stageInstDateFin;
    }

    public void setStageInstDateFin(LocalDate stageInstDateFin) {
        this.stageInstDateFin = stageInstDateFin;
    }

    public double getStageInstPrix() {
        return stageInstPrix;
    }

    public void setStageInstPrix(double stageInstPrix) {
        this.stageInstPrix = stageInstPrix;
    }

    public String getEnfantPrenom() {
        return enfantPrenom;
    }

    public void setEnfantPrenom(String enfantPrenom) {
        this.enfantPrenom = enfantPrenom;
    }

    public String getEnfantNom() {
        return enfantNom;
    }

    public void setEnfantNom(String enfantNom) {
        this.enfantNom = enfantNom;
    }
}
