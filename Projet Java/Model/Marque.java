
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Marque {
    private String nom_marque;
    private Vector<Models>ListModels=new Vector<Models>();
    private Vector<Scooter>ListScooter=new Vector<Scooter>();

    public Marque(String nom_marque) {
        this.nom_marque=nom_marque;
    }
    public void addModels(Models models){
        ListModels.add(models);
    }
    public void addScooter(Scooter scooter){
        ListScooter.add(scooter);
    }

    //getters & setters 

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