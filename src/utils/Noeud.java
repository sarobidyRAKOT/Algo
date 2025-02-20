package utils;

import java.awt.*;
import java.util.ArrayList;

import Fenetre.Arrow;
import Fenetre.P_noeud;

/** Server 
 * 
 * degré: nombre de son voisin
 * 
*/
public class Noeud {

    protected String adr_IP;
    protected ArrayList <String> sites = new ArrayList <String> (); 
    protected ArrayList <String> adrs_voisins = new ArrayList <String> ();
    protected boolean etat = true;
    protected String predecesseur;
    protected int distance;

    public boolean visited = false;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Noeud noeud = new Noeud();
        for (String site : sites) {
            noeud.sites.add(site);
        }
        for (String adrs_voisin : this.adrs_voisins) {
            noeud.adrs_voisins.add(adrs_voisin);
        }
        noeud.adr_IP = this.getAdr_IP();
        noeud.predecesseur = this.predecesseur;
        noeud.setEtat(this.isEtat());
        return noeud;
    }

    public boolean equals(Noeud noeud) {
        if (this.adr_IP.equals(noeud.adr_IP)) {
            return true; // mitovy
        }
        return false;
    }


// Constructeur ...
    private Noeud () {}
    public Noeud (String adresse_IP) {
        this.adr_IP = adresse_IP;
    }
// fin Constructeur ...

    public ArrayList <String>  get_voisins_in (ArrayList <Noeud> F) {
        /**
         * maka ards IP noeud voisins mbl 
         * tsy nasorin DIJKSTRA tao am F
         */
        ArrayList <String> new_list = new ArrayList<>();
        for (String ards_IP : this.getAdrs_voisins()) {            
            for (Noeud n : F) {
                if (n.getAdr_IP().equals(ards_IP)) {
                    new_list.add(ards_IP);
                    break;
                }
            }
        }
        return new_list;
    } 
    
    public String[] get_noeud_DISPO (ArrayList <Noeud> ALL_noeuds) {
        ArrayList <String> adrs_dispo = new ArrayList<>();
    
        for (Noeud noeud : ALL_noeuds) {
            if (!this.adrs_voisins.contains(noeud.getAdr_IP()) && !noeud.getAdr_IP().equals(this.adr_IP)) {
                adrs_dispo.add(noeud.getAdr_IP());
            }
        }
        String[] adrs_disp = new String[adrs_dispo.size()];
        for (int i = 0; i < adrs_dispo.size(); i++) {
            adrs_disp[i] = adrs_dispo.get(i);
        }
        return adrs_disp;
    }


    public boolean check_site_inLIST (String nom_site) {
        
        if (this.sites.contains(nom_site)) {
            return true;
        }
        return false;
    }


    public static void tracer_chemin (P_noeud pn_tete, P_noeud pn_queue, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        
        int epaisseurLigne = 2;
        g2d.setStroke(new BasicStroke(epaisseurLigne));
        int x1 = pn_tete.getX()+25;
        int y1 = pn_tete.getY()+40;
        int x2 = pn_queue.getX()+25;
        int y2 = pn_queue.getY()+40;

        g2d.drawLine(x1, y1, x2, y2);

        // g2d.setColor(Color.RED);

        Arrow.drawArrow(g2d, x2, y2, x1, y1);
        g2d.setStroke(new BasicStroke(1));
    }


    public boolean ajouter_voisin (String ardrs_IP) {

        if (!this.getAdrs_voisins().contains(ardrs_IP)) {
            getAdrs_voisins().add(ardrs_IP);
            return true;
        } else return false;
    }
    
    public void setEtat(boolean etat) { this.etat = etat; }
    public boolean isEtat() { return etat; }
    public void setPredecesseur(String predecesseur) {
        this.predecesseur = predecesseur;
    }

    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }
    public String getPredecesseur() {
        return predecesseur;
    }

    public String getAdr_IP() {
        return adr_IP;
    }
    public ArrayList<String> getSites() {
        return sites;
    }
    public ArrayList<String> getAdrs_voisins() { return adrs_voisins; }
}
