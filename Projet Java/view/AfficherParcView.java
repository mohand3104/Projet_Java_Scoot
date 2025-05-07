package view;

import javax.swing.*;
import java.awt.*;

public class AfficherParcView extends JFrame {

    private JTextArea parcArea;
    private JButton fermerButton;

    public AfficherParcView() {
        setTitle("LOUSCOOT - Affichage du Parc");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        parcArea = new JTextArea();
        parcArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(parcArea);

        fermerButton = new JButton("Fermer");

        add(scrollPane, BorderLayout.CENTER);
        add(fermerButton, BorderLayout.SOUTH);

        fermerButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    public void setContenu(String texte) {
        parcArea.setText(texte);
    }

    public static void main(String[] args) {
        new AfficherParcView();
    }
}