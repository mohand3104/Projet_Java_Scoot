import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServiceLocation 
{

    public static void louerScooter(Parc parc, Client client, Scanner scanner) 
  {
        System.out.print("Entrez l'identifiant du scooter à louer : ");
        int id_scooter = scanner.nextInt();
        scanner.nextLine(); // pour un saut de ligne

        Scooter scooterTrouve = null; // type scotter pour sauvegarder si trouvé

        // Recherche du scooter dans le parc
        for (Scooter scooter : parc.getListScooter()) {
            if (scooter.getId_Scooter() == id_scooter) {
                scooterTrouve = scooter;
                break;
            }
        }

        if (scooterTrouve == null) {
            System.out.println("❌ Scooter introuvable !");
            return;
        }

        // Vérifie si le scooter est déjà loué (s'il a une location sans retour)
        for (Location loc : scooterTrouve.getListlocation()) {
            if (loc.getRetour() == null) {
                System.out.println("❌ Ce scooter est déjà en location !");
                return;
            }
        }

        try {
            System.out.print("Date de début (jj/mm/aaaa) : ");
            String dateDebutStr = scanner.nextLine();
            Date date_debut = new SimpleDateFormat("dd/MM/yyyy").parse(dateDebutStr);

            System.out.print("Kilométrage initial : ");
            int km_initial = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Date de retour prévue (jj/mm/aaaa) : ");
            String dateRetourStr = scanner.nextLine();
            Date date_retour_prevue = new SimpleDateFormat("dd/MM/yyyy").parse(dateRetourStr);

            // Créer la location
            Location location = new Location(date_debut, km_initial, date_retour_prevue, scooterTrouve, client);

            // Lier la location au scooter et au client
            scooterTrouve.Location(location); // méthode déjà présente dans ta classe Scooter
            client.addLocation(location);     // méthode déjà présente dans Client

            System.out.println("✅ Location enregistrée avec succès !");

        } catch (Exception e) {
            System.out.println("❌ Erreur dans le format de date.");
        }
    }
}

