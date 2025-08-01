package com.tfe.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PanierDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idTransaction;
    @NotNull(message = "The amount is required")
    private double montant;
    @NotNull(message = "The final amount is required")
    private double montantFinal;
    @NotNull(message = "The discount rate is required")
    private double tauxReduction;

    private List<InscriptionPanierDTO> inscriptions;

    //getter & setter

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getMontantFinal() {
        return montantFinal;
    }

    public void setMontantFinal(double montantFinal) {
        this.montantFinal = montantFinal;
    }

    public double getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(double tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public List<InscriptionPanierDTO> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<InscriptionPanierDTO> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
