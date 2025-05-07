package model;

import java.io.Serializable;
import java.util.Vector;

public class Models implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nom_model;
    private int cylindree;
    private Vector<Marque> ListMarques = new Vector<>();
    private Vector<Scooter> ListScooter = new Vector<>();
    private Categories categorie;

    public Models(String nom_model, int cylindree, Categories categorie) {
        this.nom_model = nom_model;
        this.cylindree = cylindree;
        this.categorie = categorie;
    }

    public void addMarques(Marque marque) {
        ListMarques.add(marque);
    }

    public void addScooter(Scooter scooter) {
        ListScooter.add(scooter);
    }

    public String getNom_model() {
        return nom_model;
    }

    public void setNom_model(String nom_model) {
        this.nom_model = nom_model;
    }

    public int getCylindree() {
        return cylindree;
    }

    public void setCylindree(int cylindree) {
        this.cylindree = cylindree;
    }

    public Vector<Marque> getListMarques() {
        return ListMarques;
    }

    public Vector<Scooter> getListScooter() {
        return ListScooter;
    }

    public Categories getCategorie() {
        return categorie;
    }

    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }
}
