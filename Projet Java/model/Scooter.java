package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Scooter implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_scooter;
    private int kilometrage;
    private float tarifs;
    private float caution;
    private Parc parc;
    private Vector<Location> Listlocation = new Vector<>();
    private Models model;

    public Scooter(int id_scooter, int kilometrage, float tarifs, float caution, Parc parc, Models model) {
        this.id_scooter = id_scooter;
        this.kilometrage = kilometrage;
        this.tarifs = tarifs;
        this.caution = caution;
        this.parc = parc;
        this.model = model;
    }

    public void Location(Location location) {
        Listlocation.add(location);
    }

    public int getId_Scooter() {
        return id_scooter;
    }

    public void setId_Scooter(int id_scooter) {
        this.id_scooter = id_scooter;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public float getTarifs() {
        return tarifs;
    }

    public void setTarifs(float tarifs) {
        this.tarifs = tarifs;
    }

    public float getCaution() {
        return caution;
    }

    public void setCaution(float caution) {
        this.caution = caution;
    }

    public Parc getParc() {
        return parc;
    }

    public void setParc(Parc parc) {
        this.parc = parc;
    }

    public Vector<Location> getListlocation() {
        return Listlocation;
    }

    public Models getModels() {
        return model;
    }

    public void setModels(Models model) {
        this.model = model;
    }

    // Calcul automatique de l'état de location
    public boolean isEnLocation() {
        Date aujourdHui = new Date();
        for (Location loc : Listlocation) {
            Date debut = loc.getDateDebut();
            Date fin = (loc.getRetour() != null) ? loc.getRetour().getDateRetour() : loc.getDateRetourPrevue();
            if (debut.compareTo(aujourdHui) <= 0 && aujourdHui.compareTo(fin) <= 0) {
                return true;
            }
        }
        return false;
    }
}
