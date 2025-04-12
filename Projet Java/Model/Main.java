package models;
import java.util.*;


public class Main {
    public static void main(String[] args) 
    {
        // Création du parc et des scooters
        Parc parc = new Parc("azazga"); 
        Categories cat =new Categories("A12");
        Models a = new Models("Bugatti",14,cat);
        Client client = new Client("Jean","Dupont","0605630518",1);  

        // Création de quelques scooters
        Scooter scooter1 = new Scooter(1,1234,2,3,parc,a); 
        Scooter scooter2 = new Scooter(2,1234,2,3,parc,a);
        
        // Ajout des scooters au parc
        parc.addScooter(scooter1);
        parc.addScooter(scooter2);

        // Affichage des scooters disponibles avant la location
        System.out.println("Scooters disponibles avant location:");
        for (Scooter scooter : parc.getListScooter()) {
            if (!scooter.isEnLocation()) 
            { // Si le scooter n'est pas en location
                System.out.println(scooter); // Affiche les informations du scooter
            }
        }


        // Créer un scanner pour les saisies utilisateur
        Scanner scanner = new Scanner(System.in);

        // Tester la méthode louerScooter via la classe ServiceLocation
        System.out.println("\n== Test de la méthode louerScooter ==\n");
        ServiceLocation.louerScooter(parc, client, scanner);  // Louer un scooter via la classe ServiceLocation

        // Affichage des scooters après location
        System.out.println("\nScooters après location:");
        for (Scooter scooter : parc.getListScooter()) {
            if (!scooter.isEnLocation()) 
            { // Si le scooter n'est pas en location
                System.out.println(scooter); // Affiche les informations du scooter
            }
        }

        // Tester la méthode retournerScooter via la classe ServiceLocation
        System.out.println("\n== Test de la méthode retournerScooter ==\n");
        ServiceLocation serviceLocation = new ServiceLocation();
		serviceLocation.retournerScooter(parc,scanner);  // Retourner un scooter via la classe ServiceLocation

        // Affichage des scooters après le retour
        System.out.println("\nScooters après retour:");
        for (Scooter scooter : parc.getListScooter()) {
            System.out.println(scooter);
        }
    }
}
