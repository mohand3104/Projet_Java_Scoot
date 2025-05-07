
package model;

import java.io.Serializable;
import java.util.Vector;

public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nom;
    private String prenom;
    private String telephone;
    private int id;
    private Parc parc;
    private Vector<Location> ListLocations = new Vector<>();
    private Permis permis;

    public Client(String nom, String prenom, String telephone, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.id = id;
    }

    public Client(String nom, String prenom, String telephone, int id, Parc parc) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.id = id;
        this.parc = parc;
    }

    public void setPermis(Permis permis) {
        this.permis = permis;
    }

    public void addLocation(Location location) {
        ListLocations.add(location);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Parc getParc() {
        return parc;
    }

    public void setParc(Parc parc) {
        this.parc = parc;
    }

    public Vector<Location> getListLocations() {
        return ListLocations;
    }

    public Permis getPermis() {
        return permis;
    }
}
