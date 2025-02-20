package Fenetre;

import javax.swing.*;
import utils.*;

import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {
    /**
     * frame pour ajouter 
     * d'autre site
     */

    private JTextField textField;
    
    public Frame(Noeud noeud, P_principal p_principal) {
        setTitle("INFO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(p_principal); // Centrer la frame sur l'écran

        setVisible(true);


        // Création du JTextField avec libellé "Nom"
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Nom du site :"), gbc);

        gbc.gridy = 1;
        textField = new JTextField(20);
        panel.add(textField, gbc);

        // Création des boutons "Valider" et "Quitter"
        JPanel buttonPanel = new JPanel();
        JButton validerButton = new JButton("Valider");
        JButton quitterButton = new JButton("Quitter");
        buttonPanel.add(validerButton);
        buttonPanel.add(quitterButton);

        // Ajout des écouteurs d'événements aux boutons
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = textField.getText();
                if (!nom.isEmpty()) {
                    if (!noeud.check_site_inLIST(nom)) {
                        noeud.getSites().add(nom);
                        p_principal.dns.ajouter_site(nom, noeud.getAdr_IP());
                        dispose();
                    }
                } 
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Ajout des panneaux au conteneur principal (JFrame)
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

}
