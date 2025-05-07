package Controleur;

import model.*;
import view.RetournerScooterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RetournerScooterController {

    private Parc parc;
    private RetournerScooterView view;

    public RetournerScooterController(Parc parc, RetournerScooterView view) {
        this.parc = parc;
        this.view = view;

        view.getValiderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRetour();
            }
        });
    }

    private void handleRetour() {
        try {
            int idScooter = Integer.parseInt(view.getIdScooter());
            int kmRetour = Integer.parseInt(view.getKilometrageRetour());

            Scooter scooter = parc.getListScooter().stream()
                .filter(s -> s.getId_Scooter() == idScooter)
                .findFirst()
                .orElse(null);

            if (scooter == null) {
                showMessage("Scooter introuvable !");
                return;
            }

            Location locationActive = null;
            for (int i = scooter.getListlocation().size() - 1; i >= 0; i--) {
                Location loc = scooter.getListlocation().get(i);
                if (loc.getRetour() == null) {
                    locationActive = loc;
                    break;
                }
            }

            if (locationActive == null) {
                showMessage("⚠ Ce scooter n'a pas de location active.");
                return;
            }

            if (kmRetour < locationActive.getKmInitial()) {
                showMessage("❌ Le kilométrage retour ne peut pas être inférieur au départ !");
                return;
            }

            int kmEffectue = kmRetour - locationActive.getKmInitial();
            scooter.setKilometrage(scooter.getKilometrage() + kmEffectue);

            Date dateRetour = new Date();
            Retour retour = new Retour(dateRetour, kmRetour, locationActive);
            locationActive.setRetour(retour);

            Date datePrevue = locationActive.getDateRetourPrevue();
            if (dateRetour.after(datePrevue)) {
                long diffMillis = dateRetour.getTime() - datePrevue.getTime();
                long joursDeRetard = diffMillis / (1000 * 60 * 60 * 24);
                if (joursDeRetard == 0) joursDeRetard = 1;

                double montantParJour = 10.0;
                double montantPenalite = joursDeRetard * montantParJour;

                Penalite penalite = new Penalite(1, montantPenalite, "Retard de " + joursDeRetard + " jour(s)", parc);
                retour.Penalite(penalite);

                parc.Penalite(penalite);

                showMessage("⚠️ Retard de " + joursDeRetard + " jour(s). Pénalité : " + montantPenalite + "€");
            }

            showMessage("✅ Retour enregistré avec succès.\nKilométrage ajouté : " + kmEffectue + " km");

        } catch (Exception ex) {
            showMessage("❌ Erreur : " + ex.getMessage());
        }
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(view, msg);
    }
}
