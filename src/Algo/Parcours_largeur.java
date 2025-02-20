package Algo;

import java.util.ArrayList;

import utils.Graphe;
import utils.Noeud;

public class Parcours_largeur {
    
    public Graphe graphe_clone;
    public Noeud sommet;
    private boolean EFA = false;

    public Parcours_largeur (Graphe graphe, Noeud sommet) {
        this.graphe_clone = graphe;
        this.sommet = sommet;
    }


    public void lancer () {
        
        int poids = 0;
        this.sommet.visited = true;
        this.sommet.setDistance(poids);
        ArrayList <Noeud> file = new ArrayList <Noeud>();
        file.add(sommet); // ENFILER ...

        while (!file.isEmpty()) {
            ++ poids;
            Noeud v = file.remove(0); // DEFILER

            for (String voisin : v.getAdrs_voisins()) {
                Noeud u = graphe_clone.get_sommetby_adrsIP(voisin);
                if (!u.visited) { // mbol ts nolalovany
                    u.visited= true;
                    u.setDistance(poids);
                    file.add(u); // ENFILER ...
                }
            }

        }

    }

}
