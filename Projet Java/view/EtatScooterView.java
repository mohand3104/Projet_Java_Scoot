package view;

import javax.swing.*;
import java.awt.*;

public class EtatScooterView extends JFrame {

    private JTextField idScooterField;
    private JButton rechercherButton;
    private JButton annulerButton;
    private JTextArea resultatArea;

    public EtatScooterView() {
        setTitle("LOUSCOOT - État d'un Scooter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        idScooterField = new JTextField();
        rechercherButton = new JButton("Rechercher");
        topPanel.add(new JLabel("ID du scooter :"));
        topPanel.add(idScooterField);

        resultatArea = new JTextArea();
        resultatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultatArea);

        annulerButton = new JButton("Annuler");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rechercherButton);
        buttonPanel.add(annulerButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        annulerButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    public JButton getRechercherButton() {
        return rechercherButton;
    }

    public String getIdScooter() {
        return idScooterField.getText();
    }

    public void setResultat(String text) {
        resultatArea.setText(text);
    }

    public static void main(String[] args) {
        new EtatScooterView();
    }
}
