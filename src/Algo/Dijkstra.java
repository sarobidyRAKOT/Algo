package Algo;

import java.util.ArrayList;

import utils.Graphe;
import utils.Noeud;

public class Dijkstra {
    
    public Graphe graphe_clone;
    public Noeud sommet;
    protected ArrayList <Noeud> E;
    private boolean EFA = false;

    public Dijkstra (Graphe graphe, Noeud sommet) {
        this.graphe_clone = graphe;
        this.sommet = sommet;
    }

    public void lancer () {
        for (Noeud u : graphe_clone.getSommets()) {
            u.setDistance((int) Integer.MAX_VALUE);
            u.setPredecesseur(null);
        }
        this.sommet.setDistance(0);
        ArrayList <Noeud> E = new ArrayList <Noeud> ();
        ArrayList <Noeud> F = graphe_clone.getSommets();

        while (!F.isEmpty()) {
            Noeud u = EXTRAIRE_MIN(F);
            if (u != null) {
                E.add(u); // E <-- E U {u}
                // RA MISY VOISIN LE SOMMET ...
                String voisin = "";
                for (String adrs_IP : u.get_voisins_in(F)) {
                    voisin += " "+ adrs_IP;

                    Noeud v = graphe_clone.get_sommetby_adrsIP(adrs_IP);
                    int DISTANCE_uv = u.getDistance() + graphe_clone.get_Arete(u, v).getPoids();
                    if (v.getDistance() > DISTANCE_uv) {

                        System.out.println("mise a jour "+v.getAdr_IP()+" "+v.getDistance()+"-> "+DISTANCE_uv);
                        v.setDistance(DISTANCE_uv);
                        v.setPredecesseur(u.getAdr_IP());
                    }
                }

                System.out.println(u.getAdr_IP()+ " "+ u.getDistance() + " voisin: "+voisin);
            } else {
                for (Noeud noeud : F) {
                    E.add(noeud);
                }
                break;
            }
        }
        this.E = E;
    }


    private Noeud EXTRAIRE_MIN (ArrayList <Noeud> F) {
        Noeud noeud_min = null;

        int min_dist = (int) Integer.MAX_VALUE;
        Integer indice_noeud = null;
        for (int i = 0; i < F.size(); i++) {
            /**
             * maka indice noeud
             * - akaiky ndrindra
             * - serveur ts tapaka
             */
            if (F.get(i).getDistance() < min_dist && F.get(i).isEtat()) {
                min_dist = F.get(i).getDistance();
                indice_noeud = i;
            }
        }
        if (indice_noeud != null) {
            noeud_min = F.get(indice_noeud);
            F.remove((int) indice_noeud);
        }
        return noeud_min;
    }

    // private void get_noeud (String adrs_IP) {
    //     for
    // }

    private Noeud get_inE (String adrs_IP_predecesseur) {
        Noeud noeud = null;

        for (Noeud noeu : E) {
            if (noeu.getAdr_IP().equals(adrs_IP_predecesseur)) {
                noeud = noeu;
                break;
            }
        }
        return noeud;
    }

    public ArrayList <Noeud> tracer (String nom_site) {
        if (!EFA) {
            this.lancer();
        }

        Integer indice = null;
        int min_dist = (int) Integer.MAX_VALUE;

        for (int i = 0; i < this.E.size(); i++) {
            /**
             * get site akaiky ndrindra 
             * et serveur en bonne etat 
             */
            if (E.get(i).getSites().contains(nom_site) && E.get(i).getDistance() < min_dist && E.get(i).isEtat()) {
                indice = i;
                min_dist = E.get(i).getDistance();
            }

            // affiche ...
            String voisin = "";
            for (String adr : E.get(i).getAdrs_voisins()) {
                voisin += " "+adr;
            }
            System.out.println(this.E.get(i).getAdr_IP()+"("+E.get(i).getDistance()+")"+ " pred ["+E.get(i).getPredecesseur()+"] VOISIN:"+voisin);
        }
        ArrayList <Noeud> chemin = new ArrayList <Noeud>();
        if (indice != null) {
            Noeud noeud_proche = E.get(indice);
            // while (noeud_proche.getPredecesseur()) {
            System.out.println("akaiky ndrindra "+noeud_proche.getAdr_IP());
            
            chemin.add(noeud_proche);
            
            while (noeud_proche.getPredecesseur() != null) {
                
                noeud_proche = this.get_inE(noeud_proche.getPredecesseur());
                chemin.add(noeud_proche);
            }
            // }
        } else {
            System.out.println("TS POINSE");
        }

        return chemin;
        
    }
}
