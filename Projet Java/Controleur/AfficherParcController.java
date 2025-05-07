package Controleur;

import model.Parc;
import model.Scooter;
import view.AfficherParcView;

import java.util.List;

public class AfficherParcController {

    private final Parc parc;
    private final AfficherParcView view;

    public AfficherParcController(Parc parc, AfficherParcView view) {
        this.parc = parc;
        this.view = view;

        afficherListe();
    }

    private void afficherListe() {
        List<Scooter> scooters = parc.getListScooter();
        if (scooters.isEmpty()) {
            view.setContenu("🚫 Aucun scooter dans le parc.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("📋 Liste des scooters du parc :\n\n");

        for (Scooter scooter : scooters) {
            String etat = scooter.isEnLocation() ? "En location" : "Disponible";
            sb.append("ID: ").append(scooter.getId_Scooter())
              .append(" - Modèle: ").append(scooter.getModels().getNom_model())
              .append(" - Km: ").append(scooter.getKilometrage())
              .append(" - Permis requis: ").append(scooter.getModels().getCategorie().getTypePermis())
              .append(" - État: ").append(etat).append("\n");
        }

        view.setContenu(sb.toString());
    }
}
