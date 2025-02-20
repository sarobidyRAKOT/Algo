package Fenetre;

import javax.swing.*;
import utils.Arete;
import utils.Noeud;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {

    private JComboBox<String> comboBox1;
    private JTextField textField;
    private JButton validerButton, annulerButton;

    public MyFrame(String[] select, P_principal p_principal, P_noeud p_noeud_tete) {
        setTitle("Ma Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(p_principal); // Centrer la frame sur l'écran
        
        setLayout(null);
        // Création des composants
        JLabel label1 = new JLabel("Serveur:");
        comboBox1 = new JComboBox<>(select);

        JLabel label3 = new JLabel("Distance:");
        textField = new JTextField(10);

        validerButton = new JButton("Valider");
        annulerButton = new JButton("Annuler");

        // Ajout des écouteurs d'événements aux boutons
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton Valider
                // Par exemple, récupérer les valeurs des champs et les traiter
                try {
                    if (traitement_data()) {
                        String adrs_IP = (String) comboBox1.getSelectedItem();
                        Noeud noeud_queue = p_principal.graphe.get_sommetby_adrsIP(adrs_IP);
                        Arete arete = p_principal.graphe.get_Arete(p_noeud_tete.noeud, noeud_queue);
                        if (arete == null) {
                            // mbol tsy ao io noeud io 
                            // System.out.println("ajout arete SUCCESS");
                            int poids = Integer.parseInt(textField.getText());
                            p_principal.graphe.getAretes().add(new Arete(p_noeud_tete.noeud, noeud_queue, poids));
                            p_noeud_tete.noeud.getAdrs_voisins().add(noeud_queue.getAdr_IP());
                            P_noeud p_noeud_queue = get_p_noeud(noeud_queue.getAdr_IP(), p_principal.p_noeuds);
                            p_principal.p_aretes.add(new P_arete(p_noeud_tete, p_noeud_queue, poids));
                            
                        } // else System.out.println("ajout arete FAILD");
                    }
                } catch (Exception error) {
                    System.out.println("ERREUR: "+error.getMessage());
                    dispose();
                }
                dispose();
            }
        });


        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton Annuler
                dispose(); // Ferme la JFrame
            }
        });

        // Création du panneau principal
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        mainPanel.setBounds(20, 20, 250, 100);
        mainPanel.add(label1);
        mainPanel.add(comboBox1);
        mainPanel.add(label3);
        mainPanel.add(textField);
        mainPanel.add(validerButton);
        mainPanel.add(annulerButton);

        // Ajout du panneau principal à la JFrame
        add(mainPanel);

        setVisible(true);
    }

    private P_noeud get_p_noeud (String adrs_IP, ArrayList <P_noeud> list_pnoeuds) {

        P_noeud p_noeud = null;
        for (P_noeud p_noeu : list_pnoeuds) {
            if (p_noeu.noeud.getAdr_IP().equals(adrs_IP)) {
                p_noeud = p_noeu;
                break;
            }
        }
        return p_noeud;
    }

    private boolean traitement_data () throws Exception {
        String dist = this.textField.getText();
        String adrs = (String) this.comboBox1.getSelectedItem();
        boolean valid = true;
        String error = "";
        if (adrs != null && !adrs.isEmpty()) {
            int distance = Integer.parseInt(dist);
            if (distance < 0) {
                error += " La distance positif";
                valid = false;
            }
        } else {
            error += " Pas d'ITEM selectionner\n";
            valid = false;
        }

        if (valid) {
            return true;
        } else throw new Exception(error);
    }

}
