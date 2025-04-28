import java.util.Vector;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Parc {
    private String nom;
    private Vector<Client> ListClient = new Vector<>();
    private Vector<Penalite> ListPenalites = new Vector<>();
    private Vector<Scooter> ListScooter = new Vector<>();
    private Vector<Marque> ListMarque = new Vector<>();

    public Parc(String nom) {
        this.nom = nom;
    }

    public void addClient(Client client) {
        ListClient.add(client);
    }

    public void Penalite(Penalite penalite) {
        ListPenalites.add(penalite);
    }

    public void addScooter(Scooter scooter) {
        ListScooter.add(scooter);
    }

    public void addMarque(Marque marque) {
        ListMarque.add(marque);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Vector<Client> getListClient() {
        return ListClient;
    }

    public Vector<Penalite> getListPenalites() {
        return ListPenalites;
    }

    public Vector<Scooter> getListScooter() {
        return ListScooter;
    }

    public Vector<Marque> getListMarque() {
        return ListMarque;
    }

    // Nouvelle méthode pour afficher l'état d'un scooter
    public void etatScooter(Scanner scanner) {
        System.out.print("Entrez l'identifiant du scooter : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Scooter scooter : ListScooter) {
            if (scooter.getId_Scooter() == id) {
                System.out.println("\n--- État du Scooter ---");
                System.out.println("ID : " + scooter.getId_Scooter());
                System.out.println("Modèle : " + scooter.getModels().getNom_model());
                System.out.println("Kilométrage : " + scooter.getKilometrage() + " km");
                System.out.println("État : " + (scooter.isEnLocation() ? "En location" : "Disponible"));
                return;
            }
        }
        System.out.println("Scooter introuvable !");
    }

    public void afficherParc() {
        if (ListScooter.isEmpty()) {
            System.out.println("Aucun scooter dans le parc.");
            return;
        }
    
        System.out.println("\n--- État du Parc de Scooters ---");
        for (Scooter scooter : ListScooter) {
            System.out.println("ID: " + scooter.getId_Scooter()
                    + ", Modèle: " + scooter.getModels().getNom_model()
                    + ", Kilométrage: " + scooter.getKilometrage() + " km"
                    + ", État: " + (scooter.isEnLocation() ? "En location" : "Disponible"));
        }
    }

    public void afficherResumeParc() {
        if (ListScooter.isEmpty()) {
            System.out.println("🚫 Aucun scooter dans le parc.");
            return;
        }
    
        int total = ListScooter.size();
        int enLocation = 0;
        int disponible = 0;
        int totalKilometrage = 0;
    
        System.out.println("\n--- Résumé du Parc ---");
        for (Scooter scooter : ListScooter) {
            totalKilometrage += scooter.getKilometrage();
            if (scooter.isEnLocation()) {
                enLocation++;
                System.out.println("En location : ID " + scooter.getId_Scooter());
            } else {
                disponible++;
                System.out.println("Disponible : ID " + scooter.getId_Scooter());
            }
        }
    
        double kilometrageMoyen = total > 0 ? (double) totalKilometrage / total : 0;
    
        System.out.println("\nNombre total de scooters : " + total);
        System.out.println("Nombre de scooters en location : " + enLocation);
        System.out.println("Nombre de scooters disponibles : " + disponible);
        System.out.printf("Kilométrage moyen du parc : %.2f km\n", kilometrageMoyen);
    }

    public void sauvegarderParcDansFichier() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("parc_scooters.txt"))) {
            for (Scooter scooter : ListScooter) {
                writer.println(scooter.getId_Scooter() + ","
                             + scooter.getModels().getNom_model() + ","
                             + scooter.getKilometrage() + ","
                             + scooter.isEnLocation());
            }
            System.out.println("Parc sauvegardé dans le fichier parc_scooters.txt !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public void gererScooters(Scanner scanner) {
        System.out.println("\n--- Gestion du Parc de Scooters ---");
        System.out.println("1. Ajouter un nouveau scooter");
        System.out.println("2. Retirer un scooter existant");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();
    
        switch (choix) {
            case 1:
                ajouterScooter(scanner);
                break;
            case 2:
                retirerScooter(scanner);
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }
    
    private void ajouterScooter(Scanner scanner) {
        System.out.print("Entrez l'identifiant du scooter : ");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Entrez le modèle du scooter : ");
        String modele = scanner.nextLine();
    
        System.out.print("Entrez le kilométrage du scooter : ");
        int kilometrage = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Entrez le tarif journalier : ");
        float tarif = scanner.nextFloat();
        scanner.nextLine();
    
        System.out.print("Entrez la caution : ");
        float caution = scanner.nextFloat();
        scanner.nextLine();
    
        Categories cat = new Categories("A1"); // tu peux ajuster si besoin
        Models model = new Models(modele, 125, cat);
        Scooter nouveauScooter = new Scooter(id, kilometrage, tarif, caution, this, model);
    
        addScooter(nouveauScooter);
    
        System.out.println("Nouveau scooter ajouté au parc !");
    }
    
    private void retirerScooter(Scanner scanner) {
        System.out.print("Entrez l'identifiant du scooter à retirer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        for (Scooter scooter : ListScooter) {
            if (scooter.getId_Scooter() == id) {
                ListScooter.remove(scooter);
                System.out.println("Scooter retiré du parc !");
                return;
            }
        }
        System.out.println("Scooter introuvable !");
    }
    
    
}
