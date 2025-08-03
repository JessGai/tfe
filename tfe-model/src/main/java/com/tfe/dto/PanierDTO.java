package com.tfe.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PanierDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idPanier;
    private int idParent;
    private int idEnfant;
    private int idStageInstance;

    //getter & setter

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public int getIdStageInstance() {
        return idStageInstance;
    }

    public void setIdStageInstance(int idStageInstance) {
        this.idStageInstance = idStageInstance;
    }
}
