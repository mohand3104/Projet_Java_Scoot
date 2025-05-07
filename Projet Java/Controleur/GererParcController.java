package Controleur;

import model.*;
import view.GererParcView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GererParcController {

    private final Parc parc;
    private final GererParcView view;

    public GererParcController(Parc parc, GererParcView view) {
        this.parc = parc;
        this.view = view;

        view.getAjouterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterScooter();
            }
        });

        view.getRetirerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirerScooter();
            }
        });
    }

    private void ajouterScooter() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID du scooter :"));
            String modele = JOptionPane.showInputDialog("Modèle :");
            int km = Integer.parseInt(JOptionPane.showInputDialog("Kilométrage :"));
            float tarif = Float.parseFloat(JOptionPane.showInputDialog("Tarif journalier :"));
            float caution = Float.parseFloat(JOptionPane.showInputDialog("Caution :"));
            String permis = JOptionPane.showInputDialog("Permis requis (A1 ou A2) :").toUpperCase();

            Categories cat = new Categories(permis);
            Models model = new Models(modele, 125, cat);
            Scooter scooter = new Scooter(id, km, tarif, caution, parc, model);
            parc.addScooter(scooter);

            JOptionPane.showMessageDialog(view, "Scooter ajouté avec succès.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erreur : " + ex.getMessage());
        }
    }

    private void retirerScooter() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID du scooter à retirer :"));
            Scooter target = parc.getListScooter().stream()
                .filter(s -> s.getId_Scooter() == id)
                .findFirst()
                .orElse(null);
            if (target != null) {
                parc.getListScooter().remove(target);
                JOptionPane.showMessageDialog(view, "Scooter retiré du parc.");
            } else {
                JOptionPane.showMessageDialog(view, "Scooter non trouvé.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erreur : " + ex.getMessage());
        }
    }
}
