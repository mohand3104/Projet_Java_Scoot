package view;

import javax.swing.*;
import java.awt.*;
import model.Parc;
import Controleur.*;

public class MenuPrincipalView extends JFrame {

    private JButton louerButton;
    private JButton retournerButton;
    private JButton etatButton;
    private JButton afficherButton;
    private JButton gestionButton;
    private JButton quitterButton;
    private Parc parc;

    public MenuPrincipalView(Parc parc) {
        this.parc = parc;
        setTitle("LOUSCOOT - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        Font font = new Font("Segoe UI", Font.PLAIN, 16);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setBackground(Color.WHITE);

        louerButton = new JButton("🛵 Louer un scooter");
        retournerButton = new JButton("🔄 Retourner un scooter");
        etatButton = new JButton("🔍 État d'un scooter");
        afficherButton = new JButton("📋 Afficher l'état du parc");
        gestionButton = new JButton("🛠️ Gérer le parc");
        quitterButton = new JButton("❌ Quitter");

        JButton[] buttons = {louerButton, retournerButton, etatButton, afficherButton, gestionButton, quitterButton};
        for (JButton btn : buttons) {
            btn.setFont(font);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setPreferredSize(new Dimension(300, 40));
            btn.setMaximumSize(new Dimension(350, 50));
            panel.add(Box.createVerticalStrut(10));
            panel.add(btn);
        }

        louerButton.addActionListener(e -> new LouerScooterView(parc));
        retournerButton.addActionListener(e -> {
            RetournerScooterView retourView = new RetournerScooterView();
            new RetournerScooterController(parc, retourView);
        });
        etatButton.addActionListener(e -> {
            EtatScooterView etatView = new EtatScooterView();
            new EtatScooterController(parc, etatView);
        });
        afficherButton.addActionListener(e -> {
            AfficherParcView view = new AfficherParcView();
            new AfficherParcController(parc, view);
        });
        gestionButton.addActionListener(e -> {
            GererParcView view = new GererParcView();
            new GererParcController(parc, view);
        });
        quitterButton.addActionListener(e -> {
            parc.sauvegarderParc();
            System.exit(0);
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Parc parc = Parc.chargerParc();
        new MenuPrincipalView(parc);
    }
}
