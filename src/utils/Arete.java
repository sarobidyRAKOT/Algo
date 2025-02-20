package utils;


/**
 * relation entre deux noeud
 * boucle: une arete partant d'une sommet vers lui-meme
 */
public class Arete {
    
    Noeud noeud_tete; // depart de la flèche
    Noeud noeud_queue; // arrivé de la flèche
    int poids; // (en fonction du traitement)


// Constructeur 
    public Arete (Noeud tete, Noeud queue, int poids) {
        this.setNoeud_tete(tete);
        this.setNoeud_queue(queue);
        this.poids = poids;
    }
// fin Constructeur 

    public boolean equals (Noeud noeud_tete, Noeud noeud_queue) {
        if (this.noeud_tete.equals(noeud_tete) 
        && this.noeud_queue.equals(noeud_queue)) {
            return true;
        }
        return false;
    }


// private SETTERS 
    private void setNoeud_queue(Noeud noeud_queue) {
        this.noeud_queue = noeud_queue;
    }
    private void setNoeud_tete(Noeud noeud_tete) {
        this.noeud_tete = noeud_tete;
    }
// fin private SETTERS 
    public Noeud getNoeud_queue() { return noeud_queue; }
    public Noeud getNoeud_tete() { return noeud_tete; }
    public int getPoids() {
        return poids;
    }



}
