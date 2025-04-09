
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Retour {
    private Date date_retour;
    private int km_retour;
    private Location location;
    private Vector <Penalite>ListPenalites=new Vector<Penalite>();


    public Retour(Date date_retour,int km_retour,Location location) {
        this.date_retour=date_retour;
        this.km_retour=km_retour;
        this.location=location;
    }

    public void Penalite(Penalite penalite){
        ListPenalites.add(penalite);
    }

    //getters & setters 

    public Date getDateRetour() {
        return date_retour;
    }
    
    public void setDateRetour(Date date_retour) {
        this.date_retour = date_retour;
    }
    
    public int getKmRetour() {
        return km_retour;
    }
    
    public void setKmRetour(int km_retour) {
        this.km_retour = km_retour;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public Vector<Penalite> getListPenalites() {
        return ListPenalites;
    }
    


}