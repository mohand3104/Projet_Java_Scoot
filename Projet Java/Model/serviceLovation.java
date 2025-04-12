import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServiceLocation 
{

    public static void louerScooter(Parc parc, Client client, Scanner scanner) // Louer scooter
  {
        System.out.print("Entrez l'identifiant du scooter à louer : ");
        int id_scooter = scanner.nextInt();
        scanner.nextLine(); // pour un saut de ligne

        Scooter scooterTrouve = null; // type scotter pour sauvegarder si trouvé

        // Recherche du scooter dans le parc
        for (Scooter scooter : parc.getListScooter()) //récupére la liste des scotter du parc
        {
            if (scooter.getId_Scooter() == id_scooter) //chercher notre scooter à partir de cette liste 
            {
                scooterTrouve = scooter;// notre scotter
                break;
            }
        }

        if (scooterTrouve == null) 
        {
            System.out.println("❌ Scooter introuvable !");
            return;
        }

        // Vérifie si le scooter est déjà loué (s'il a une location sans retour)
        for (Location loc : scooterTrouve.getListlocation()) // à partir de notre scooter on accéder à ces locations
        {
            if (loc.getRetour() == null) //si il y a pas de date de retour
            {
                System.out.println("❌ Ce scooter est déjà en location !");
                return;
            }
        }
//sinon
        try {
            System.out.print("Date de début (jj/mm/aaaa) : ");
            String dateDebutStr = scanner.nextLine(); //format string
            Date date_debut = new SimpleDateFormat("dd/MM/yyyy").parse(dateDebutStr); //conversion vers Date

            int km_initial = scooterTrouve.getKilometrage(); // récupéré automatiquement

            System.out.print("Date de retour prévue (jj/mm/aaaa) : ");
            String dateRetourStr = scanner.nextLine();
            Date date_retour_prevue = new SimpleDateFormat("dd/MM/yyyy").parse(dateRetourStr);

            // Créer la location
            Location location = new Location(date_debut, km_initial, date_retour_prevue, scooterTrouve, client);

            // Lier la location au scooter et au client
            scooterTrouve.Location(location); // méthode déjà présente dans ta classe Scooter
            client.addLocation(location);     // méthode déjà présente dans Client
            
            // Mettre à jour le statut du scooter pour qu'il soit en location
            scooterTrouve.setEnLocation(true); // Le scooter est désormais en location
 

            System.out.println("✅ Location enregistrée avec succès !");

        } catch (Exception e) {
            System.out.println("❌ Erreur dans le format de date.");
        }
    }



    
    public void retournerScooter(Parc parc,Scanner scanner) //Retoure du scooter
    {
    System.out.print("🔎 Entrez l'identifiant du scooter à retourner : ");
    int idRecherche = scanner.nextInt();
    scanner.nextLine(); // pour vider la ligne

    Scooter scooterTrouve = null;
    for (Scooter scooter : parc.getListScooter()) 
    {
        if (scooter.getId_Scooter() == idRecherche) {
            scooterTrouve = scooter;
            break;
        }
    }

    if (scooterTrouve == null) {
        System.out.println("❌ Scooter introuvable.");
        return;
    } // c'est tranquille ici

    if (scooterTrouve.getListlocation().isEmpty()) //le scooter n'est pas loué
    {
        System.out.println("⚠ Ce scooter n’est pas actuellement loué."); // logique
        return;
    }

    Location derniereLocation = scooterTrouve.getListlocation().lastElement();// recupérer la location la plus récente

    if (derniereLocation.getRetour() != null) //si cette location a un  objet retour donc c'est retourné
    {
        System.out.println("⚠ Ce scooter a déjà été retourné.");
        return;
    }

    try {
        System.out.print(" Entrez le kilométrage actuel (retour) : ");
        int kmRetour = scanner.nextInt();
        scanner.nextLine();
        //Le kilométrage actuel (au moment du retour) dépend de ce que le client a roulé avec le scooter , c'est manuelle 

        if (kmRetour < derniereLocation.getKmInitial()) //C'est logique
        {
            System.out.println("❌ Le kilométrage de retour ne peut pas être inférieur au kilométrage initial.");
            return;
        }

        int kmEffectue = kmRetour - derniereLocation.getKmInitial();
        scooterTrouve.setKilometrage(scooterTrouve.getKilometrage() + kmEffectue);//modifier le kilométrage
        /*
        Le kilométrage total du scooter est déjà connu avant la location (ex : 10 000 km).

        Le kilométrage saisi au retour (ex : 10 300) inclut tout le kilométrage depuis la création du scooter.

        Donc si tu fais + kmRetour, tu vas ajouter une deuxième fois tout le kilométrage, ce qui double la valeur par erreur.
        c'est 20300 au lieu de 10300 , imagine dans plusieurs location !!!
        */

        Date dateRetour = new Date(); // aujourd'hui
        Retour retour = new Retour(dateRetour, kmRetour, derniereLocation);
        derniereLocation.setRetour(retour);

        // Marquer le scooter comme disponible
        scooterTrouve.setEnLocation(false); // Mettre à jour le statut du scooter
        

        System.out.println("✅ Scooter retourné avec succès !");
        System.out.println("📈 Kilométrage ajouté : " + kmEffectue + " km");
        System.out.println("🔄 Le scooter est maintenant disponible.");
    } catch (Exception e) {
        System.out.println("❌ Erreur de saisie.");
    }
}


}

