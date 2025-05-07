package model;

import java.io.Serializable;
import java.util.Vector;

public class Marque implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nom_marque;
    private Vector<Models> ListModels = new Vector<>();
    private Vector<Scooter> ListScooter = new Vector<>();

    public Marque(String nom_marque) {
        this.nom_marque = nom_marque;
    }

    public void addModels(Models models) {
        ListModels.add(models);
    }

    public void addScooter(Scooter scooter) {
        ListScooter.add(scooter);
    }

    public String getNomMarque() {
        return nom_marque;
    }

    public void setNomMarque(String nom_marque) {
        this.nom_marque = nom_marque;
    }

    public Vector<Models> getListModels() {
        return ListModels;
    }

    public Vector<Scooter> getListScooter() {
        return ListScooter;
    }
}
