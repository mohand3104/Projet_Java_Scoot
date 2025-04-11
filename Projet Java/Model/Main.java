/*import java.util.*;

public class Main {
    public static void main(String[] args) {
        //permis
        Categories catA = new Categories("A");

        //modèle
        Models model1 = new Models("TMAX", 560, catA);
        catA.addModels(model1);

        //marque
        Marque yamaha = new Marque("Yamaha");
        yamaha.addModels(model1);
        model1.addMarques(yamaha);

        //parc
        Parc parc1 = new Parc("Parc Central");

        //scooter
        Scooter scooter1 = new Scooter(1, 1500, 25.0f, 100.0f, parc1,model1);
        model1.addScooter(scooter1);
        yamaha.addScooter(scooter1);
        parc1.addScooter(scooter1);

        //client
        Client client1 = new Client("Akhemoum", "SidAli", "0123456789", 1, parc1);
        parc1.addClient(client1);

        //permis
        Permis permis1 = new Permis(123456789, new Date(), new Date(), client1);
        client1.setPermis(permis1);
        permis1.addCategories(catA);
        catA.addPermis(permis1);

        //location
        Location location1 = new Location(new Date(), 1500, new Date(), scooter1, client1);
        client1.addLocation(location1);
        scooter1.Location(location1);

        //retour
        Retour retour1 = new Retour(new Date(), 1700, location1);
        location1.setRetour(retour1);

        //pénalité
        Penalite pen1 = new Penalite(1, 50.0, "Retard", parc1);
        retour1.Penalite(pen1);
        pen1.addRetour(retour1);
        parc1.Penalite(pen1);


        System.out.println("Client: " + client1.getNom() + " " + client1.getPrenom());
        System.out.println("Scooter loué: " + scooter1.getId_Scooter() + ", modèle: " + scooter1.getModels().getNom_model()+"Marque: " + scooter1.getModels().getListMarques().get(0).getNomMarque());
        System.out.println("Parc: " + parc1.getNom());
        System.out.println("Catégorie du permis: " + catA.getTypePermis());
    }
}
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Parc parc = new Parc("Parc Central");

        // Exemple de scooter et client
        Models model = new Models("XPro", 125, new Categories("A1"));
        Scooter scooter = new Scooter(1, 5000, 20.0f, 150.0f, parc, model);
        parc.addScooter(scooter);

        Client client = new Client("Doe", "John", "0612345678", 101, parc);
        parc.addClient(client);

        // Appel de la fonction louer
        ServiceLocation.louerScooter(parc, client, scanner);
    }
}
