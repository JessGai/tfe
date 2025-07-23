package com.tfe.dto;

import com.tfe.entity.ParentEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParentWithChildrenDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //ParentWithChildren
    private int idParent;
    private String email;
    private String nomParent;
    private String prenomParent;
    private String adresse;
    private int codePostal;
    private String commune;
    private String telephone1;
    private String telephone2;
    private String auth0UserId;
    private LocalDateTime dateCreation;
    private LocalDateTime lastUpdate;
    private List<EnfantDTO> enfants;
    //constructeur par défaut
    public ParentWithChildrenDTO(){
        this.enfants = new ArrayList<>();
    }
    //constructeur a partir de ParentEntity
    public ParentWithChildrenDTO(ParentEntity parent){
        this.idParent = parent.getIdParent();
        this.email = parent.getEmail();
        this.nomParent = parent.getNomParent();
        this.prenomParent = parent.getPrenomParent();
        this.adresse = parent.getAdresse();
        this.codePostal = parent.getCodePostal();
        this.commune = parent.getCommune();
        this.telephone1 = parent.getTelephone1();
        this.telephone2 = parent.getTelephone2();
        this.auth0UserId = parent.getAuth0UserId();
        this.dateCreation = parent.getDateCreation();
        this.lastUpdate = parent.getLastUpdate();
        this.enfants = new ArrayList<>();
    }

    //getter & setter
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

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
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

    public List<EnfantDTO> getEnfants() {
        return enfants;
    }

    public void setEnfants(List<EnfantDTO> enfants) {
        this.enfants = enfants;
    }
}
