package com.tfe.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="enfant")
public class EnfantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnfant;

    @ManyToOne
    @JoinColumn(name = "fk_idParent", referencedColumnName = "idParent", nullable = false)
    private ParentEntity parent;
    @Column(nullable = false)
    private String nomEnfant;
    @Column(nullable = false)
    private String prenomEnfant;
    @Column(nullable = false)
    private LocalDate dateNaissance;

    private String commentaire;

    @Column(nullable = false)
    private LocalDateTime dateCreation;
    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    //remplissage de la date la 1ere fois
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    //repmlissage uniquement si update
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    //getter & setter

    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public ParentEntity getParent() {
        return parent;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nom) {
        this.nomEnfant = nom;
    }

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenom) {
        this.prenomEnfant = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
