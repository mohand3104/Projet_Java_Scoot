package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServiceLocation {

    public void louerScooter(Parc parc, Scanner scanner) {
        boolean continuerLocation = true;

        while (continuerLocation) {
            System.out.print("Entrez le nom du client : ");
            String nom = scanner.nextLine().trim();
            System.out.print("Entrez le prénom du client : ");
            String prenom = scanner.nextLine().trim();

            Client client = null;
            for (Client c : parc.getListClient()) {
                if (c.getNom().equalsIgnoreCase(nom) && c.getPrenom().equalsIgnoreCase(prenom)) {
                    client = c;
                    break;
                }
            }

            if (client == null) {
                System.out.println("Nouveau client, veuillez compléter ses informations :");
                System.out.print("Téléphone : ");
                String telephone = scanner.nextLine();
                int id_client = parc.getListClient().size() + 1; // ID auto-incrémenté
                client = new Client(nom, prenom, telephone, id_client);
                parc.addClient(client);

                System.out.print("Type de permis du client (A1 ou A2) : ");
                String typePermis = scanner.nextLine().toUpperCase();
                Categories categorie = new Categories(typePermis);
                Permis permis = new Permis(id_client, new Date(), new Date(), client);
                permis.addCategories(categorie);
                client.setPermis(permis);
            }

            System.out.print("Entrez l'identifiant du scooter à louer : ");
            int id_scooter = scanner.nextInt();
            scanner.nextLine();

            Scooter scooterTrouve = null;
            for (Scooter scooter : parc.getListScooter()) {
                if (scooter.getId_Scooter() == id_scooter) {
                    scooterTrouve = scooter;
                    break;
                }
            }

            if (scooterTrouve == null) {
                System.out.println("Scooter introuvable !");
            } else if (client.getPermis() == null ||
                       (!client.getPermis().getListCategories().contains(scooterTrouve.getModels().getCategorie()) &&
                       !client.getPermis().getListCategories().stream().anyMatch(cat -> cat.getTypePermis().equals("A2")))) {
                System.out.println("Le client ne possède pas le permis requis pour ce scooter !");
            } else {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    System.out.print("Date de début (jj/MM/yyyy) : ");
                    Date dateDebut = sdf.parse(scanner.nextLine());

                    System.out.print("Date de retour prévue (jj/MM/yyyy) : ");
                    Date dateRetourPrevue = sdf.parse(scanner.nextLine());

                    Date aujourdHuiSansHeure = sdf.parse(sdf.format(new Date()));

                    if (dateDebut.before(aujourdHuiSansHeure)) {
                        System.out.println("La date de début ne peut pas être dans le passé !");
                        continue;
                    }

                    if (dateDebut.after(dateRetourPrevue)) {
                        System.out.println("La date de début doit être avant la date de retour !");
                        continue;
                    }

                    boolean chevauchement = false;
                    for (Location loc : scooterTrouve.getListlocation()) {
                        Date locDebut = loc.getDateDebut();
                        Date locFin = (loc.getRetour() != null) ? loc.getRetour().getDateRetour() : loc.getDateRetourPrevue();
                        if (dateDebut.compareTo(locFin) <= 0 && locDebut.compareTo(dateRetourPrevue) <= 0) {
                            chevauchement = true;
                            break;
                        }
                    }

                    if (chevauchement) {
                        System.out.println("Ce scooter est déjà réservé pendant cette période !");
                    } else {
                        Location location = new Location(dateDebut, scooterTrouve.getKilometrage(), dateRetourPrevue, scooterTrouve, client);
                        scooterTrouve.Location(location);
                        client.addLocation(location);
                        System.out.println("Location enregistrée avec succès !");
                    }

                } catch (Exception e) {
                    System.out.println("Erreur dans la saisie des dates.");
                }
            }

            System.out.print("\nVoulez-vous louer un autre scooter ? (O/N) : ");
            String reponse = scanner.nextLine().trim().toUpperCase();
            if (!reponse.equals("O")) {
                continuerLocation = false;
            }
        }
    }

    public void retournerScooter(Parc parc, Scanner scanner) {
        // Code existant du retour reste inchangé...
    }
}
