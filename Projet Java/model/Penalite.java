package model;

import java.io.Serializable;
import java.util.Vector;

public class Penalite implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_penalite;
    private double montant;
    private String description;
    private Parc parc;
    private Vector<Retour> ListRetour = new Vector<>();

    public Penalite(int id_penalite, double montant, String description, Parc parc) {
        this.id_penalite = id_penalite;
        this.montant = montant;
        this.description = description;
        this.parc = parc;
    }

    public void addRetour(Retour retour) {
        ListRetour.add(retour);
    }

    public int getIdPenalite() {
        return id_penalite;
    }

    public void setIdPenalite(int id_penalite) {
        this.id_penalite = id_penalite;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Parc getParc() {
        return parc;
    }

    public void setParc(Parc parc) {
        this.parc = parc;
    }

    public Vector<Retour> getListRetour() {
        return ListRetour;
    }
}