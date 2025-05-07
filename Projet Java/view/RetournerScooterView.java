package view;

import javax.swing.*;
import java.awt.*;

public class RetournerScooterView extends JFrame {

    private JTextField idScooterField;
    private JTextField kmRetourField;
    private JButton validerButton;
    private JButton annulerButton;

    public RetournerScooterView() {
        setTitle("LOUSCOOT - Retour d'un Scooter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        idScooterField = new JTextField();
        kmRetourField = new JTextField();

        validerButton = new JButton("Valider");
        annulerButton = new JButton("Annuler");

        panel.add(new JLabel("ID du scooter :"));
        panel.add(idScooterField);
        panel.add(new JLabel("Kilométrage retour :"));
        panel.add(kmRetourField);
        panel.add(validerButton);
        panel.add(annulerButton);

        add(panel);

        validerButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Retour enregistré pour le scooter ID: " + idScooterField.getText() +
                "\nKilométrage retour: " + kmRetourField.getText());
        });

        annulerButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    public String getIdScooter() {
        return idScooterField.getText();
    }

    public String getKilometrageRetour() {
        return kmRetourField.getText();
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public static void main(String[] args) {
        new RetournerScooterView();
    }
}
