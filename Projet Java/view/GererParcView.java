package view;

import javax.swing.*;
import java.awt.*;

public class GererParcView extends JFrame {

    private JButton ajouterButton;
    private JButton retirerButton;
    private JButton fermerButton;

    public GererParcView() {
        setTitle("LOUSCOOT - Gérer le Parc de Scooters");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Définir une police plus moderne
        Font font = new Font("Segoe UI", Font.PLAIN, 16);

        // Panel principal avec BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setBackground(Color.WHITE);

        ajouterButton = new JButton("➕ Ajouter un scooter");
        retirerButton = new JButton("➖ Retirer un scooter");
        fermerButton = new JButton("❌ Fermer");

        // Style boutons
        for (JButton btn : new JButton[]{ajouterButton, retirerButton, fermerButton}) {
            btn.setFont(font);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setPreferredSize(new Dimension(200, 40));
            btn.setMaximumSize(new Dimension(300, 50));
            panel.add(Box.createVerticalStrut(15));
            panel.add(btn);
        }

        fermerButton.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JButton getRetirerButton() {
        return retirerButton;
    }

    public static void main(String[] args) {
        new GererParcView();
    }
}
