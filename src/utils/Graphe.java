package utils;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
    
    ArrayList <Noeud> sommets;
    ArrayList <Arete> aretes;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Graphe graphe = new Graphe();
        for (Noeud noeud : this.sommets) {
            graphe.sommets.add((Noeud) noeud.clone());
        }
        for (Arete arete : aretes) {
            Noeud noeud_tete = get_sommet(graphe.sommets, arete.getNoeud_tete());
            Noeud noeud_queue = get_sommet(graphe.sommets, arete.getNoeud_queue());

            graphe.aretes.add(new Arete(noeud_tete, noeud_queue, arete.getPoids()));
        }
        return graphe;
    }

    private Noeud get_sommet (List <Noeud> list_noeuds, Noeud noeud_) {
        Noeud noeud_trouver = null;
        for (Noeud noeud : list_noeuds) {
            if (noeud.equals(noeud_)) { // verification par ards_IP
                noeud_trouver = noeud;
                break;
            }
        }
        return noeud_trouver;
    }

    public boolean is_inLIST_noueds (Noeud nouveau_noeud) {

        boolean ef_ao = false;
        for (Noeud noeud : this.getSommets()) {
            if (noeud.equals(nouveau_noeud)) {
                ef_ao = true;
            }
        }

        if (!ef_ao) {
            return true; // ajouter avec succes
        } else {
            return false;
        }
    }

    public Noeud get_sommetby_adrsIP (String adrs_IP) {
        Noeud noeud_trouver = null;

        for (Noeud noeud : this.sommets) {
            if (noeud.adr_IP.equals(adrs_IP)) {
                noeud_trouver = noeud;
                break;
            }
        }
        return noeud_trouver;
    }

    public void suprimer_voisin (Noeud noeud) {

        for (Noeud noeu : this.sommets) {
            if (noeu.adrs_voisins.contains(noeud.getAdr_IP())) {
                noeu.adrs_voisins.remove(noeud.getAdr_IP());
            }
        }
    }
    public ArrayList <Arete> get_aretes_miala (Noeud noeud) {
        ArrayList <Arete> list_aretes_miala = new ArrayList<>();
        for (Arete arete : aretes) {
            if (arete.noeud_queue.equals(noeud) || arete.noeud_tete.equals(noeud)) {
                list_aretes_miala.add(arete);
            }
        }
        return list_aretes_miala;
    }

    public Arete get_Arete (Noeud tete, Noeud queue) {
        Arete arete_trv = null;
        for (Arete arete : aretes) {
            if (arete.equals(tete, queue)) {
                arete_trv = arete;
                break;
            }
        }
        return arete_trv;
    }

// Constructeur
    public Graphe () {
        this.aretes = new ArrayList <Arete>();
        this.sommets = new ArrayList <Noeud>();
    }
// fin Constructeur

    public ArrayList<Noeud> getSommets() {
        return sommets;
    }
    public ArrayList<Arete> getAretes() {
        return aretes;
    }
}
