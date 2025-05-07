package model;

import java.io.Serializable;
import java.util.Date;

public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date date_debut;
    private int km_initial;
    private Date date_retour_prevue;
    private Scooter scooter;
    private Retour retour;
    private Client client;

    public Location(Date date_debut, int km_initial, Date date_retour_prevue) {
        this.date_debut = date_debut;
        this.km_initial = km_initial;
        this.date_retour_prevue = date_retour_prevue;
    }

    public Location(Date date_debut, int km_initial, Date date_retour_prevue, Scooter scooter, Client client) {
        this.date_debut = date_debut;
        this.km_initial = km_initial;
        this.date_retour_prevue = date_retour_prevue;
        this.scooter = scooter;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRetour(Retour retour) {
        this.retour = retour;
    }

    public Date getDateDebut() {
        return date_debut;
    }

    public void setDateDebut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public int getKmInitial() {
        return km_initial;
    }

    public void setKmInitial(int km_initial) {
        this.km_initial = km_initial;
    }

    public Date getDateRetourPrevue() {
        return date_retour_prevue;
    }

    public void setDateRetourPrevue(Date date_retour_prevue) {
        this.date_retour_prevue = date_retour_prevue;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    public Retour getRetour() {
        return retour;
    }
}
