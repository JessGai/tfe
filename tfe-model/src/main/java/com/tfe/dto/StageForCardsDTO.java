package com.tfe.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class StageForCardsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public StageForCardsDTO(int idStageDesc, String titre, String theme, String description,
                            int ageMin, int ageMax, int prix, LocalDate dateDebut, LocalDate dateFin,
                            int nbrParticipant, int nbrInscrit, Boolean statut, int idStageInst) {
        this.idStageDesc = idStageDesc;
        this.titre = titre;
        this.theme = theme;
        this.description = description;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrParticipant = nbrParticipant;
        this.nbrInscrit = nbrInscrit;
        this.statut = statut;
        this.idStageInst = idStageInst;
    }
    private int idStageDesc;
    private String titre;
    private String theme;
    private String description;
    private int ageMin;
    private int ageMax;
    private int prix;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nbrParticipant;
    private int nbrInscrit;
    private Boolean statut;
    private int idStageInst;

    public int getIdStageDesc() {
        return idStageDesc;
    }

    public String getTitre() {
        return titre;
    }

    public String getTheme() {
        return theme;
    }

    public String getDescription() {
        return description;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public int getPrix() {
        return prix;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public int getNbrParticipant() {
        return nbrParticipant;
    }

    public int getNbrInscrit() {
        return nbrInscrit;
    }

    public Boolean getStatut() {
        return statut;
    }

    public int getIdStageInst() {
        return idStageInst;
    }
}
