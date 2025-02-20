package Fenetre;

import java.awt.*;
import java.util.ArrayList;

import utils.Noeud;

public class P_arete {
    

    P_noeud pn_tete, pn_queue;
    int poids;

    public P_arete (P_noeud p_noeud_tete, P_noeud p_noeud_queue, int poids) {
        this.pn_queue = p_noeud_queue;
        this.poids = poids;
        this.pn_tete = p_noeud_tete;
    }


    public void draw (Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.GRAY);

        
        int epaisseurLigne = 2;
        g2d.setStroke(new BasicStroke(epaisseurLigne));
        int x1 = pn_tete.getX()+25;
        int y1 = pn_tete.getY()+40;
        int x2 = pn_queue.getX()+25;
        int y2 = pn_queue.getY()+40;

        g2d.drawLine(x1, y1, x2, y2);

        // g2d.setColor(Color.RED);

        Arrow.drawArrow(g2d, x1, y1, x2, y2);

        int xM = (x1 + x2) / 2;
        int yM = ((y1 + y2) / 2) + 1;

        // Dessiner un cercle pour marquer le point médian
        g2d.setColor(Color.WHITE);
        int rayon = 30;
        g2d.fillOval(xM - rayon / 2, yM - rayon / 2, rayon, rayon);
        String texte = ""+poids;
        Font font = new Font("Arial", Font.BOLD, 10);
        g2d.setFont(font);
        // Définir la couleur noire pour le texte
        g2d.setColor(Color.BLACK);

        // Obtenir les dimensions du texte
        FontMetrics fm = g2d.getFontMetrics();
        int largeurTexte = fm.stringWidth(texte);
        int hauteurTexte = fm.getHeight();

        // Dessiner le texte au centre du cercle
        g2d.drawString(texte, xM - largeurTexte / 2, yM + hauteurTexte / 4); // Correction verticale pour centrer le texte
        
        g2d.setStroke(new BasicStroke(1));
    }


    public static ArrayList <P_arete> get_p_arete_queue_tete (ArrayList <P_arete> list_p_arete, Noeud noeud) {
        ArrayList <P_arete> p_aretes = new ArrayList<>();

        for (P_arete p_arete : list_p_arete) {
            if (p_arete.pn_queue.noeud.equals(noeud) || p_arete.pn_tete.noeud.equals(noeud)) {
                p_aretes.add(p_arete);
            }
        }
        return p_aretes;
    }

}


