package ecouteur;

import java.awt.event.*;

import javax.swing.SwingUtilities;

import Fenetre.P_principal;

public class Listener extends MouseAdapter {

    P_principal p_principal;

    public Listener(P_principal p) {
        this.p_principal = p;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            p_principal.setTemp_posServeur(e.getPoint());
            p_principal.getPopupMenu().show(p_principal, e.getX(), e.getY());
        } else {
            if (p_principal.getP_noeud_selected() != null) {
                p_principal.getP_noeud_selected().selected = false;
            }
        }

    }


}
