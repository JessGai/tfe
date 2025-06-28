package com.tfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ParentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int idParent;

    @NotNull(message = "The email is required")
    private String email;

    @NotNull(message = "The name is required")
    private String nomParent;

    @NotNull(message = "The surname is required")
    private String prenomParent;

    @NotNull(message = "The address is required")
    private String adresse;

    @NotNull(message = "The postal code is required")
    private Integer codePostal;

    @NotNull(message = "The city is required")
    private String commune;

    @NotNull(message = "The phone number is required")
    private String telephone1;

    private String telephone2;
    private String auth0UserId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastUpdate;

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomParent() {
        return nomParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public String getPrenomParent() {
        return prenomParent;
    }

    public void setPrenomParent(String prenomParent) {
        this.prenomParent = prenomParent;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getAuth0UserId() {
        return auth0UserId;
    }

    public void setAuth0UserId(String auth0UserId) {
        this.auth0UserId = auth0UserId;
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
