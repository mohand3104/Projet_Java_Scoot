package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Permis implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_permis;
    private Date date_delivrance;
    private Date date_expiration;
    private Client client;
    private Vector<Categories> ListCategories = new Vector<>();

    public Permis(int id_permis, Date date_delivrance, Date date_expiration, Client client) {
        this.id_permis = id_permis;
        this.date_delivrance = date_delivrance;
        this.date_expiration = date_expiration;
        this.client = client;
    }

    public void addCategories(Categories categories) {
        ListCategories.add(categories);
    }

    public int getIdPermis() {
        return id_permis;
    }

    public void setIdPermis(int id_permis) {
        this.id_permis = id_permis;
    }

    public Date getDateDelivrance() {
        return date_delivrance;
    }

    public void setDateDelivrance(Date date_delivrance) {
        this.date_delivrance = date_delivrance;
    }

    public Date getDateExpiration() {
        return date_expiration;
    }

    public void setDateExpiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vector<Categories> getListCategories() {
        return ListCategories;
    }
}
