package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Retour implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date date_retour;
    private int km_retour;
    private Location location;
    private Vector<Penalite> ListPenalites = new Vector<>();

    public Retour(Date date_retour, int km_retour, Location location) {
        this.date_retour = date_retour;
        this.km_retour = km_retour;
        this.location = location;
    }

    public void Penalite(Penalite penalite) {
        ListPenalites.add(penalite);
    }

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

    public boolean isRetoureLate() {
        return this.date_retour.after(this.getLocation().getDateRetourPrevue());
    }
}
