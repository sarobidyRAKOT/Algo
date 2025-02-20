package Fenetre;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import utils.*;
import ecouteur.Listener;

public class P_principal extends JPanel {

    protected Graphe graphe = new Graphe();
    DNS dns = new DNS();

    protected ArrayList <P_noeud> p_noeuds = new ArrayList <P_noeud> ();
    protected ArrayList <P_arete> p_aretes = new ArrayList<P_arete>();
    protected ArrayList <Noeud> chemin = new ArrayList <Noeud>();
    private P_principal p_principal = this;
    private P_noeud p_noeud_selected;

    JPopupMenu popupMenu;
    private Point temp_posServeur;

    P_noeud Pnoeud;
    public P_principal () {
    
        Rectangle rect = new Rectangle(0, 0, WIDTH, HEIGHT);
        this.setBounds(rect);
        this.setLayout(null);

        init_popupMenu(); // popupMenu ...
        this.addMouseListener(new Listener(this));
    }


    private void init_popupMenu () {
        this.popupMenu = new JPopupMenu();

        JMenuItem ajouter_noeud = new JMenuItem("  Ajouter Noeud");
        ajouter_noeud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = temp_posServeur.x-10;
                int y = temp_posServeur.y-20;
                String adrs_IP = JOptionPane.showInputDialog(p_principal, "Adresse IP:");
                if (!adrs_IP.equals("")) {
                    // RAH MISY LE ADRS IP 
                    Noeud nouveau_noeud = new Noeud(adrs_IP);
                    if (graphe.is_inLIST_noueds(nouveau_noeud)) {
                        graphe.getSommets().add(nouveau_noeud);
                        P_noeud p_noeud = new P_noeud(nouveau_noeud, x, y, p_principal);
                        p_noeuds.add(p_noeud); // ajouter dans la liste p_noeuds
                    } else {
                        System.out.println("ef ao");
                    }
                    nouveau_noeud = null;
                }
            }
            
        });

        popupMenu.add(ajouter_noeud);

    }

    private P_noeud get_p_Noeud (Noeud noeud) {
        P_noeud pn = null;
        for (P_noeud pnoeud : this.p_noeuds) {
            if (pnoeud.noeud.equals(noeud)) {
                pn = pnoeud;
                break;
            }
        }
        return pn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // this.setBackground(Color.BLUE);     
        // Pnoeud.draw_icon(g);        
        // Pnoeud.paint(g); 
        removeAll();
        for (P_noeud p_noeud : this.p_noeuds) {
            p_noeud.draw_icon(g);
            this.add(p_noeud);
        }
        for (P_arete p_arete : this.p_aretes) { 
            p_arete.draw(g);
        }
        if (chemin.size() > 1) {
            P_noeud noeud_tete = get_p_Noeud(chemin.get(0));
            P_noeud noeud_queue = null;           
            for (int i = 1; i < chemin.size(); i ++) {
                noeud_queue = get_p_Noeud(chemin.get(i));
                // paint 
                Noeud.tracer_chemin(noeud_tete, noeud_queue, g);
                // end paint
                noeud_tete = noeud_queue;
            }
        }


        repaint();
    }
    

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
    public void setTemp_posServeur(Point point) {
        this.temp_posServeur = point;
    }
    private static int WIDTH = F_principal.WIDTH - 15;
    private static int HEIGHT = F_principal.HEIGHT - 35;

    public P_noeud getP_noeud_selected() {
        return p_noeud_selected;
    }
    public void setP_noeud_selected(P_noeud p_noeud_selected) {
        this.p_noeud_selected = p_noeud_selected;
    }
}
