import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parc parc = new Parc("LOUSCOOT");
        Categories cat = new Categories("A1");
        Models model = new Models("Bugatti", 125, cat);

        Client client = new Client("Jean", "Dupont", "0605630518", 1);



        ServiceLocation serviceLocation = new ServiceLocation();
        Scanner scanner = new Scanner(System.in);

        boolean continuer = true;

        while (continuer) {
            System.out.println("\n========= MENU LOUSCOOT =========");
            System.out.println("1. Louer un scooter");
            System.out.println("2. Retourner un scooter");
            System.out.println("3. État d'un scooter");
            System.out.println("4. Affichage de l'état du parc de scooters");
            System.out.println("5. Ajouter ou Retirer un scooter du parc");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // consommer retour ligne

            switch (choix) {
                case 1:
                    serviceLocation.louerScooter(parc, scanner);
                    break;
                case 2:
                    serviceLocation.retournerScooter(parc, scanner);
                    break;
                case 3:
                    parc.etatScooter(scanner);
                    break;
                case 4:
                    parc.afficherParc();
                    break;
                case 5:
                    parc.gererScooters(scanner);
                    break;
                case 6:
                    System.out.println("✅ Sauvegarde et fermeture du programme...");
                    parc.sauvegarderParcDansFichier();
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide. Essayez encore !");
            }
        }

        scanner.close();
    }
}
