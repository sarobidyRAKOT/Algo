package Fenetre;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Algo.Dijkstra;
import Algo.Parcours_largeur;
import utils.Arete;
import utils.Graphe;
import utils.Noeud;
import ecouteur.Listener_noeud;

public class P_noeud extends JPanel {
    
    protected Noeud noeud;
    ImageIcon icon = new ImageIcon("../assets/images/3.png");
    public static int TAILLE = 50;
    P_principal p_principal;

    JPopupMenu popupMenu;
    P_noeud THIS_p_noeud = this;
    JLabel label; // id server(poids)

    public boolean selected = false;
    JMenuItem dasactive_noeud;


    public P_noeud(Noeud noeud, int x, int y, P_principal p_principal) {
        this.noeud = noeud; // ajouter le noeud dans le panel
        this.p_principal = p_principal;
        this.setLayout(null);
        this.setBounds(x, y, TAILLE, TAILLE+10);


        this.popupMenu = _initialiser_POPUPMENU();
        
        // Initialisation du JLabel
        // this.setBackground(Color.red);
        this.label = new JLabel(this.noeud.getAdr_IP());
        this.label.setBounds(22, 0, 100, 20); // Définissez les coordonnées et la taille du JLabel
        this.add(label); // Ajout du JLabel à votre JPanel

        this.addMouseListener(new Listener_noeud(this));
        this.addMouseMotionListener(new Listener_noeud(this));
    }

    private JPopupMenu _initialiser_POPUPMENU () {

        JPopupMenu popupMenu = new JPopupMenu();
        dasactive_noeud = new JMenuItem("Desactiver noeud");
        JMenuItem ajouter_voisin = new JMenuItem("Ajouter voisin");
        JMenuItem ajouter_site = new JMenuItem("Ajouter un autre site");
        JMenuItem lancer_dijkstra = new JMenuItem("Lancer DIJKSTRA");
        JMenuItem supprimer_noeud = new JMenuItem("Supprimer Noeud");
        
        
        dasactive_noeud.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) { 
                
                if (noeud.isEtat()) {
                    noeud.setEtat(false); 
                    dasactive_noeud.setText("Activer Noeud");
                    
                } else {
                    noeud.setEtat(true);
                    dasactive_noeud.setText("Desactiver Noeud");

                }
            } 
        });
        supprimer_noeud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                p_principal.p_noeuds.remove(THIS_p_noeud);

                p_principal.graphe.getSommets().remove(noeud);
                p_principal.graphe.suprimer_voisin(noeud);
                ArrayList <Arete> aretes_miala = p_principal.graphe.get_aretes_miala(noeud);
                ArrayList <P_arete> p_aretes_miala = P_arete.get_p_arete_queue_tete(p_principal.p_aretes, noeud);

                p_principal.p_aretes.removeAll(p_aretes_miala);
                p_principal.graphe.getAretes().removeAll(aretes_miala);

                p_aretes_miala.removeAll(p_aretes_miala);
                aretes_miala.removeAll(aretes_miala);
            }
        });
        ajouter_site.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Frame(noeud, p_principal);
            }
        });
        listener_ajouter_voisin(ajouter_voisin);
        listener_lancer_DIJKSTRA(lancer_dijkstra);

        // AJOUTER DANS LE PopupMenu ...
            popupMenu.add(dasactive_noeud);
            popupMenu.add(ajouter_site);
            popupMenu.add(lancer_dijkstra);
            popupMenu.add(ajouter_voisin);
            popupMenu.add(supprimer_noeud);
        // end ajout ...
        return popupMenu;
    }

    private void listener_lancer_DIJKSTRA (JMenuItem item_lacerDIKSTRA) {
        item_lacerDIKSTRA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] sites = p_principal.dns.get_allSITES();

                // for (int i = 0; i < graphe.allsites.size(); i++) {
                //     sites[i] = graphe.allsites.get(i);
                //     // System.out.println(noeudsDispo.get(i).getAdress_ip());
                // }
                JComboBox<String> comboBox = new JComboBox<>(sites);
                JOptionPane.showMessageDialog(THIS_p_noeud, comboBox, "Sélectionner un voisin", JOptionPane.QUESTION_MESSAGE);
                String site_selected = (String) comboBox.getSelectedItem();

                if (site_selected != null && !site_selected.isEmpty()) {
                    // System.out.println(site_selected);
                    Graphe graphe_clone;
                    try {
                        graphe_clone = (Graphe) p_principal.graphe.clone();
                        Noeud sommet = graphe_clone.get_sommetby_adrsIP(noeud.getAdr_IP());

                        Dijkstra dijkstra = new Dijkstra(graphe_clone, sommet);
                        p_principal.chemin = dijkstra.tracer(site_selected);
                        for (int i = p_principal.chemin.size()-1; i >= 0; i--) {
                            System.out.print(""+p_principal.chemin.get(i).getAdr_IP()+"("+p_principal.chemin.get(i).getDistance()+")->");
                        } 
                        if (p_principal.chemin.size() > 0) {
                            System.out.println("Tonga : [" + site_selected+"]");
                        } else System.out.println("Tonga : [null]");

                        // Parcours_largeur parcours_largeur = new Parcours_largeur(graphe_clone, sommet);
                        // parcours_largeur.lancer();
                        // for (Noeud noeud : graphe_clone.getSommets()) {
                        //     System.out.println(noeud.getAdr_IP()+" "+noeud.getDistance());
                        // }

                    } catch (CloneNotSupportedException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
    }

    private void listener_ajouter_voisin (JMenuItem item_ajout_voisin) {
        item_ajout_voisin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] noeudsDispo = noeud.get_noeud_DISPO(p_principal.graphe.getSommets());
                new MyFrame(noeudsDispo, p_principal, THIS_p_noeud);
                
            }
        });
    }

    public void draw_icon (Graphics graphics) {
        // graphics.drawImage(this.icon.getImage(), this.getX(), this.getY()+10, null);
        graphics.drawImage(this.icon.getImage(), this.getX()+10, this.getY()+25, 30, 30, null);
    }



    @Override
    public void paintComponent (Graphics g) {
        // paintComponant
        //  vide mintsy 
        // super.paintComponent(g);
        if (!this.noeud.isEtat()) {
            g.setColor(Color.RED);
            g.drawOval(5, 20, 40, 40);            
        } 
        if (selected) {            
            g.setColor(Color.GREEN);
            g.drawOval(5, 20, 40, 40);
        }

    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
    public Noeud getNoeud() {
        return noeud;
    }
    public P_principal getP_principal() {
        return p_principal;
    }


}
