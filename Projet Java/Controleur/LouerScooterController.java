package Controleur;

import view.LouerScooterView;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LouerScooterController {

    private Parc parc;
    private LouerScooterView view;

    public LouerScooterController(Parc parc, LouerScooterView view) {
        this.parc = parc;
        this.view = view;

        view.getLouerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLocation();
            }
        });
    }

    private void handleLocation() {
        try {
            String nom = view.getNom();
            String prenom = view.getPrenom();
            String telephone = view.getTelephone();
            String permis = view.getPermis().toUpperCase();
            int idScooter = Integer.parseInt(view.getIdScooter());
            String dateDebutStr = view.getDateDebut();
            String dateRetourStr = view.getDateRetour();

            Client client = parc.getListClient().stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nom) && c.getPrenom().equalsIgnoreCase(prenom))
                .findFirst()
                .orElse(null);

            if (client == null) {
                int id = parc.getListClient().size() + 1;
                client = new Client(nom, prenom, telephone, id);
                Permis nouveauPermis = new Permis(id, new Date(), new Date(), client);
                nouveauPermis.addCategories(new Categories(permis));
                client.setPermis(nouveauPermis);
                parc.addClient(client);
            }

            Scooter scooter = parc.getListScooter().stream()
                .filter(s -> s.getId_Scooter() == idScooter)
                .findFirst()
                .orElse(null);

            if (scooter == null) {
                showMessage(" Scooter introuvable !");
                return;
            }

            String permisRequis = scooter.getModels().getCategorie().getTypePermis();

            boolean permisOK = client.getPermis().getListCategories().stream().anyMatch(cat -> {
                String type = cat.getTypePermis();
                return type.equals(permisRequis) || type.equals("A2");
            });
            

            if (!permisOK) {
                showMessage("Le client ne possède pas le permis requis pour ce scooter !");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateDebut = sdf.parse(dateDebutStr);
            Date dateRetour = sdf.parse(dateRetourStr);
            
            Date aujourdHuiSansHeure = sdf.parse(sdf.format(new Date()));

            if (dateDebut.before(aujourdHuiSansHeure)) {
            showMessage("❌ La date de début ne peut pas être dans le passé !");
            return;
            }


            if (dateDebut.after(dateRetour)) {
                showMessage("La date de début doit être avant la date de retour !");
                return;
            }

            for (Location loc : scooter.getListlocation()) {
                Date debut = loc.getDateDebut();
                Date fin = (loc.getRetour() != null) ? loc.getRetour().getDateRetour() : loc.getDateRetourPrevue();
                if (dateDebut.compareTo(fin) <= 0 && debut.compareTo(dateRetour) <= 0) {
                    showMessage("Ce scooter est déjà réservé à ces dates !");
                    return;
                }
            }

            Location location = new Location(dateDebut, scooter.getKilometrage(), dateRetour, scooter, client);
            scooter.Location(location);
            client.addLocation(location);
            showMessage("Location enregistrée avec succès !");

        } catch (Exception ex) {
            showMessage("Erreur : " + ex.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(view, message);
    }
}
