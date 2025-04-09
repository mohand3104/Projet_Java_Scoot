
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Parc {
    private String nom;
    private Vector<Client>ListClient=new Vector<Client>();
    private Vector<Penalite>ListPenalites=new Vector<Penalite>();
    private Vector<Scooter>ListScooter=new Vector<Scooter>();
    private Vector<Marque>ListMarque=new Vector<Marque>();


    public Parc(String nom) {
        this.nom=nom;
    }
    public void addClient(Client client){
        ListClient.add(client);
    }
    public void Penalite(Penalite penalite){
        ListPenalites.add(penalite);
    }
    public void addScooter(Scooter scooter){
        ListScooter.add(scooter);
    }
    public void addMarque(Marque marque){
        ListMarque.add(marque);
    }

    //getters & setters 

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Vector<Client> getListClient() {
        return ListClient;
    }
    
    public Vector<Penalite> getListPenalites() {
        return ListPenalites;
    }
    
    public Vector<Scooter> getListScooter() {
        return ListScooter;
    }
    public Vector<Marque> getListMarque() {
        return ListMarque;
    }
    
}