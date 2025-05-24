package com.tfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class StageDescDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idStageDesc;

    @NotBlank(message = "The title is required")
    @JsonProperty("titre")
    private String titre;

    @NotBlank(message = "The theme is required")
    @JsonProperty("theme")
    private String theme;

    @NotBlank(message = "The description is required")
    @Size(max = 500, message = "The description cannot exceed 500 characters")
    @JsonProperty("description")
    private String description;

    @Min(value = 3, message = "Minimum age must be at least 3 years")
    @JsonProperty("ageMin")
    private int ageMin;

    @Min(value = 4, message = "Maximum age must be at least 4 years")
    @JsonProperty("ageMax")
    private int ageMax;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    @JsonProperty(value = "dCreation", access = JsonProperty.Access.READ_ONLY)
    private Date dCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    @JsonProperty(value = "dUpdate", access = JsonProperty.Access.READ_ONLY)
    private Date dUpdate;

    // Getters and Setters

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
}