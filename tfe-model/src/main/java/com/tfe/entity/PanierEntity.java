package com.tfe.entity;

import jakarta.persistence.*;

@Entity
@Table(name="panier")
public class PanierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPanier;

    @ManyToOne
    @JoinColumn(name = "fk_idParent", referencedColumnName = "idParent", nullable = false)
    private ParentEntity parent;

    @ManyToOne
    @JoinColumn(name = "fk_idEnfant", referencedColumnName = "idEnfant", nullable = false)
    private EnfantEntity enfant;

    @ManyToOne
    @JoinColumn(name = "fk_idStageInst", referencedColumnName = "id_stage_instance", nullable = false)
    private StageInstanceEntity stageInstance;

    //etter and setter

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public ParentEntity getParent() {
        return parent;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

    public EnfantEntity getEnfant() {
        return enfant;
    }

    public void setEnfant(EnfantEntity enfant) {
        this.enfant = enfant;
    }

    public StageInstanceEntity getStageInstance() {
        return stageInstance;
    }

    public void setStageInstance(StageInstanceEntity stageInstance) {
        this.stageInstance = stageInstance;
    }
}
