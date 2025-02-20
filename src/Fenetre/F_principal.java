package Fenetre;

import javax.swing.*;

/**
 * Frame principal
 */

public class F_principal extends JFrame {
    
    public static int HEIGHT = 800;
    public static int WIDTH = 800;
    ImageIcon icon = new ImageIcon("../assets/ico/serveur-cloud.png");

    // CONSTRUCTEUR ...
    public F_principal () {

        this.setTitle("CHEMIN PLUS COURS");
        setIconImage(icon.getImage());
        this.setBounds(0, 0, this.getWIDTH(), this.getHEIGHT());

        this.setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setResizable(false);
        setVisible(true); // rendre visible 
    }


    public int getWIDTH() { return WIDTH; }
    public int getHEIGHT() { return HEIGHT; }
}
