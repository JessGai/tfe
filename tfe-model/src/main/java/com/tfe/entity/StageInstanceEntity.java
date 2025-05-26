package com.tfe.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="stage_instance")
public class StageInstanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stage_instance")
    private int idStageInst;

    @Column(name = "prix",nullable = false)
    private int prix;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "nbr_participant", nullable = false)
    private int nbrParticipant;

    @Column(name = "nbr_Inscrit")
    private int nbrInscrit;

    @Column(name="statut_visible")
    private boolean statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage_desc", referencedColumnName = "id_stage_desc", nullable = false)
    private StageDescEntity stageDesc;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    //remplissage de la date la 1ere fois
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateUpdate = LocalDateTime.now();
    }

    //repmlissage uniquement si update
    @PreUpdate
    protected void onUpdate() {
        dateUpdate = LocalDateTime.now();
    }

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

    public StageDescEntity getStageDesc() {
        return stageDesc;
    }

    public void setStageDesc(StageDescEntity stageDesc) {
        this.stageDesc = stageDesc;
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
