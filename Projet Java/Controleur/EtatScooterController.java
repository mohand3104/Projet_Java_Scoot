package Controleur;


import model.Parc;
import model.Scooter;
import view.EtatScooterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtatScooterController {

    private Parc parc;
    private EtatScooterView view;

    public EtatScooterController(Parc parc, EtatScooterView view) {
        this.parc = parc;
        this.view = view;

        view.getRechercherButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRecherche();
            }
        });
    }

    private void handleRecherche() {
        try {
            int id = Integer.parseInt(view.getIdScooter());
            Scooter scooter = parc.getListScooter().stream()
                    .filter(s -> s.getId_Scooter() == id)
                    .findFirst()
                    .orElse(null);

            if (scooter == null) {
                view.setResultat("❌ Scooter introuvable.");
            } else {
                String etat = scooter.isEnLocation() ? "En location" : "Disponible";
                String infos = "\nID : " + scooter.getId_Scooter() +
                        "\nModèle : " + scooter.getModels().getNom_model() +
                        "\nKilométrage : " + scooter.getKilometrage() + " km" +
                        "\nPermis requis : " + scooter.getModels().getCategorie().getTypePermis() +
                        "\nÉtat : " + etat;
                view.setResultat(infos);
            }
        } catch (NumberFormatException e) {
            view.setResultat("❌ ID invalide.");
        }
    }
}

