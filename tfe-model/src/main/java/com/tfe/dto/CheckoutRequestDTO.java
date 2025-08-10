package com.tfe.dto;

import java.io.Serial;
import java.io.Serializable;

public class CheckoutRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private double montant;
    private int idParent;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }
}
