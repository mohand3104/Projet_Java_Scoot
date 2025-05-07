
package model;

import java.io.Serializable;
import java.util.Vector;

public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type_permis;
    private Vector<Permis> ListPermis = new Vector<>();
    private Vector<Models> ListModels = new Vector<>();

    public Categories(String type_permis) {
        this.type_permis = type_permis;
    }

    public void addPermis(Permis permis) {
        ListPermis.add(permis);
    }

    public void addModels(Models models) {
        ListModels.add(models);
    }

    public String getTypePermis() {
        return type_permis;
    }

    public void setTypePermis(String type_permis) {
        this.type_permis = type_permis;
    }

    public Vector<Permis> getListPermis() {
        return ListPermis;
    }

    public Vector<Models> getListModels() {
        return ListModels;
    }

    @Override
    public String toString() {
        return "Catégorie : " + type_permis;
    }
}
