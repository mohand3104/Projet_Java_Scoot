package view;

import javax.swing.*;
import java.awt.*;
import model.Parc;
import Controleur.LouerScooterController;

public class LouerScooterView extends JFrame {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField telephoneField;
    private JTextField permisField;
    private JTextField idScooterField;
    private JTextField dateDebutField;
    private JTextField dateRetourField;
    private JButton louerButton;
    private JButton annulerButton;

    public LouerScooterView(Parc parc) {
        setTitle("LOUSCOOT - Louer un Scooter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        nomField = new JTextField();
        prenomField = new JTextField();
        telephoneField = new JTextField();
        permisField = new JTextField();
        idScooterField = new JTextField();
        dateDebutField = new JTextField();
        dateRetourField = new JTextField();

        louerButton = new JButton("Louer");
        annulerButton = new JButton("Annuler");

        panel.add(new JLabel("Nom du client :"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom du client :"));
        panel.add(prenomField);
        panel.add(new JLabel("Téléphone :"));
        panel.add(telephoneField);
        panel.add(new JLabel("Permis (A1 ou A2) :"));
        panel.add(permisField);
        panel.add(new JLabel("ID du scooter :"));
        panel.add(idScooterField);
        panel.add(new JLabel("Date de début (jj/MM/yyyy) :"));
        panel.add(dateDebutField);
        panel.add(new JLabel("Date de retour prévue (jj/MM/yyyy) :"));
        panel.add(dateRetourField);

        panel.add(louerButton);
        panel.add(annulerButton);

        add(panel);

        annulerButton.addActionListener(e -> dispose());

        new LouerScooterController(parc, this);

        setVisible(true);
    }

    public JButton getLouerButton() {
        return louerButton;
    }

    public String getNom() {
        return nomField.getText();
    }

    public String getPrenom() {
        return prenomField.getText();
    }

    public String getTelephone() {
        return telephoneField.getText();
    }

    public String getPermis() {
        return permisField.getText();
    }

    public String getIdScooter() {
        return idScooterField.getText();
    }

    public String getDateDebut() {
        return dateDebutField.getText();
    }

    public String getDateRetour() {
        return dateRetourField.getText();
    }
}
