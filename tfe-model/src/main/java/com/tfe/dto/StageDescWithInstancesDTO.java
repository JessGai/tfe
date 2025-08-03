package com.tfe.dto;

import com.tfe.entity.StageDescEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StageDescWithInstancesDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // StageDescWithInstancesDTO
        private int idStageDesc;
        private String titre;
        private String theme;
        private String description;
        private int ageMin;
        private int ageMax;
        private Date dCreation;
        private Date dUpdate;

        // Liste des instances associées
        private List<StageInstDto> instances;

        // Constructeur par défaut
        public StageDescWithInstancesDTO() {
            this.instances = new ArrayList<>();
        }

        // Constructeur à partir de StageDescEntity
        public StageDescWithInstancesDTO(StageDescEntity stageDesc) {
            this.idStageDesc = stageDesc.getIdStageDesc();
            this.titre = stageDesc.getTitre();
            this.theme = stageDesc.getTheme();
            this.description = stageDesc.getDescription();
            this.ageMin = stageDesc.getAgeMin();
            this.ageMax = stageDesc.getAgeMax();
            this.dCreation = stageDesc.getdCreation();
            this.dUpdate = stageDesc.getdUpdate();
            this.instances = new ArrayList<>();
        }

        // Constructeur à partir de StageDescDTO
        public StageDescWithInstancesDTO(StageDescDTO stageDescDTO) {
            this.idStageDesc = stageDescDTO.getIdStageDesc();
            this.titre = stageDescDTO.getTitre();
            this.theme = stageDescDTO.getTheme();
            this.description = stageDescDTO.getDescription();
            this.ageMin = stageDescDTO.getAgeMin();
            this.ageMax = stageDescDTO.getAgeMax();
            this.dCreation = stageDescDTO.getdCreation();
            this.dUpdate = stageDescDTO.getdUpdate();
            this.instances = new ArrayList<>();
        }

        // Getters et Setters
        public int getIdStageDesc() {
            return idStageDesc;
        }

        public void setIdStageDesc(int idStageDesc) {
            this.idStageDesc = idStageDesc;
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

        public Date getDCreation() {
            return dCreation;
        }

        public void setDCreation(Date dCreation) {
            this.dCreation = dCreation;
        }

        public Date getDUpdate() {
            return dUpdate;
        }

        public void setDUpdate(Date dUpdate) {
            this.dUpdate = dUpdate;
        }

        public List<StageInstDto> getInstances() {
            return instances;
        }

        public void setInstances(List<StageInstDto> instances) {
            this.instances = instances;
        }



}
