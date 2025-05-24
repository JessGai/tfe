package com.tfe.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="stage_desc")
public class StageDescEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stage_desc")
    private int idStageDesc;

    @Column(name = "titre",nullable = false)
    private String titre;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "age_min",nullable = false)
    private int ageMin;

    @Column(name = "age_max", nullable = false)
    private int ageMax;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "d_creation", nullable = false)
    private Date dCreation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "d_update")

    private Date dUpdate;

    //remplissage de la date la 1ere fois
    @PrePersist
    protected void onCreate() {
        dCreation = new Date();
        dUpdate = new Date();
    }

    //repmlissage uniquement si update
    @PreUpdate
    protected void onUpdate() {
        dUpdate = new Date();
    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public Date getdCreation() {
        return dCreation;
    }

    public void setdCreation(Date dCreation) {
        this.dCreation = dCreation;
    }

    public Date getdUpdate() {
        return dUpdate;
    }

    public void setdUpdate(Date dUpdate) {
        this.dUpdate = dUpdate;
    }

    public int getIdStageDesc() {
        return idStageDesc;
    }

    public void setIdStageDesc(int idStageDesc) {
        this.idStageDesc = idStageDesc;
    }
}