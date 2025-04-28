import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServiceLocation {

    public void louerScooter(Parc parc, Scanner scanner) {
        boolean continuerLocation = true;

        while (continuerLocation) {
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
                System.out.println("❌ Scooter introuvable !");
            } else {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    System.out.print("Date de début (jj/MM/yyyy) : ");
                    Date dateDebut = sdf.parse(scanner.nextLine());

                    System.out.print("Date de retour prévue (jj/MM/yyyy) : ");
                    Date dateRetourPrevue = sdf.parse(scanner.nextLine());

                    Date aujourdHuiSansHeure = sdf.parse(sdf.format(new Date()));

                    if (dateDebut.before(aujourdHuiSansHeure)) {
                        System.out.println("❌ La date de début ne peut pas être dans le passé !");
                        continue;
                    }

                    if (dateDebut.after(dateRetourPrevue)) {
                        System.out.println("❌ La date de début doit être avant la date de retour !");
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
                        System.out.println("❌ Ce scooter est déjà réservé pendant cette période !");
                    } else {
                        Location location = new Location(dateDebut, scooterTrouve.getKilometrage(), dateRetourPrevue);
                        scooterTrouve.Location(location);

                        System.out.println("✅ Location enregistrée avec succès !");
                    }

                } catch (Exception e) {
                    System.out.println("❌ Erreur dans la saisie des dates.");
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
        System.out.print("Entrez l'identifiant du scooter à retourner : ");
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
            System.out.println("❌ Scooter introuvable !");
            return;
        }

        if (scooterTrouve.getListlocation().isEmpty()) {
            System.out.println("⚠ Ce scooter n'a jamais été loué.");
            return;
        }

        Location locationActive = null;
        for (int i = scooterTrouve.getListlocation().size() - 1; i >= 0; i--) {
            Location loc = scooterTrouve.getListlocation().get(i);
            if (loc.getRetour() == null) {
                locationActive = loc;
                break;
            }
        }

        if (locationActive == null) {
            System.out.println("⚠ Ce scooter a déjà été retourné ou aucune location active.");
            return;
        }

        try {
            System.out.print("Entrez le kilométrage actuel : ");
            int kmRetour = scanner.nextInt();
            scanner.nextLine();

            if (kmRetour < locationActive.getKmInitial()) {
                System.out.println("❌ Le kilométrage au retour ne peut pas être inférieur au départ !");
                return;
            }

            int kmEffectue = kmRetour - locationActive.getKmInitial();
            scooterTrouve.setKilometrage(scooterTrouve.getKilometrage() + kmEffectue);

            Date dateRetour = new Date();
            Retour retour = new Retour(dateRetour, kmRetour, locationActive);
            locationActive.setRetour(retour);

            Date datePrevue = locationActive.getDateRetourPrevue();
            if (dateRetour.after(datePrevue)) {
                long diffMillis = dateRetour.getTime() - datePrevue.getTime();
                long joursDeRetard = diffMillis / (1000 * 60 * 60 * 24);
                if (joursDeRetard == 0) {
                    joursDeRetard = 1;
                }

                double montantParJour = 10.0;
                double montantPenalite = joursDeRetard * montantParJour;

                Penalite penalite = new Penalite(1, montantPenalite, "Retard de " + joursDeRetard + " jour(s)", parc);
                retour.Penalite(penalite);
                parc.Penalite(penalite);

                System.out.println("⚠️ Scooter rendu en retard de " + joursDeRetard + " jour(s) !");
                System.out.println("💸 Pénalité appliquée : " + montantPenalite + "€.");
            }

            System.out.println("✅ Scooter retourné avec succès !");
            System.out.println("📈 Kilométrage ajouté : " + kmEffectue + " km");

        } catch (Exception e) {
            System.out.println("❌ Erreur dans la saisie du kilométrage.");
        }
    }
}
