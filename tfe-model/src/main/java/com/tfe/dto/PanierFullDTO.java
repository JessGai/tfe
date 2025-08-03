package com.tfe.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PanierFullDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private double montantTotal;
    private double tauxReduction;
    private double montantAvecReduction;
    List<PanierListForFullPanierDTO> liste;

    public PanierFullDTO() {
        liste = new ArrayList<>();
    }

    //getter et setter:


    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public double getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(double tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public double getMontantAvecReduction() {
        return montantAvecReduction;
    }

    public void setMontantAvecReduction(double montantAvecReduction) {
        this.montantAvecReduction = montantAvecReduction;
    }

    public List<PanierListForFullPanierDTO> getListe() {
        return liste;
    }

    public void setListe(List<PanierListForFullPanierDTO> liste) {
        this.liste = liste;
    }
}
