
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Categories {
    private String type_permis;
    private Vector<Permis>ListPermis=new Vector<Permis>();
    private Vector<Models>ListModels=new Vector<Models>();


    public Categories(String type_permis) {
        this.type_permis=type_permis;
    }

    public void addPermis(Permis permis){
        ListPermis.add(permis);
    }
    public void addModels(Models models){
        ListModels.add(models);
    }


    //getters & setters 
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
    

}