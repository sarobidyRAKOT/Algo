package ecouteur;

import java.awt.event.*;
import javax.swing.*;
import Fenetre.P_noeud;

public class Listener_noeud extends MouseAdapter {
    
    P_noeud p_noeud;
    public Listener_noeud (P_noeud p) {
        this.p_noeud = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (SwingUtilities.isRightMouseButton(e)) {
            p_noeud.getPopupMenu().show(p_noeud, e.getX(), e.getY());
        } else {
            if (p_noeud.getP_principal().getP_noeud_selected() != null) {
                p_noeud.getP_principal().getP_noeud_selected().selected = false;
            }
            p_noeud.selected = true;
            p_noeud.getP_principal().setP_noeud_selected(p_noeud);
            
            // manapotra description serveur
            String desc = "Adresse IP: "+p_noeud.getNoeud().getAdr_IP()+"\n";
            desc += "Voisins: [";
            for (String voisin : p_noeud.getNoeud().getAdrs_voisins()) {
                desc += " "+voisin+" ";
            } desc += "]\nSites: [";
            for (String site : p_noeud.getNoeud().getSites()) {
                desc += " "+site+" ";
            } desc += "]\nETAT: "+p_noeud.getNoeud().isEtat();
            JOptionPane.showMessageDialog(p_noeud, desc);
        }
    }

    
    private int initialMouseX, initialMouseY;
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        initialMouseX = e.getX()-p_noeud.getX();
        initialMouseY = e.getY()-p_noeud.getY();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (p_noeud.getP_principal().getP_noeud_selected() != null) {
            p_noeud.getP_principal().getP_noeud_selected().selected = false;
        }
        p_noeud.selected = true;
        p_noeud.getP_principal().setP_noeud_selected(p_noeud);
       
        // deplacer le serveur ...
        int dx = e.getX() - initialMouseX;
        int dy = e.getY() - initialMouseY;
        int newX = p_noeud.getX() + dx;
        int newY = p_noeud.getY() + dy;
        p_noeud.setLocation(newX-20, newY-30);
    }
    
}
