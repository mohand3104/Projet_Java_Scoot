
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Models {
    private String nom_model;
    private int cylindree;
    private Vector<Marque>ListMarques=new Vector<Marque>();
    private Vector<Scooter>ListScooter=new Vector<Scooter>();
    private Categories categorie;

    public Models(String nom_model,int cylindree,Categories categories) {
        this.nom_model=nom_model;
        this.cylindree=cylindree;
        this.categorie=categorie;
    }
    public void addMarques(Marque marque){
        ListMarques.add(marque);
    }

    public void addScooter(Scooter scooter){
        ListScooter.add(scooter);
    }

    //getters & setters 

    public String getNom_model() {
        return nom_model;
    }
    
    public void setNsom_model(String nom_model) {
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