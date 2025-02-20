package Fenetre;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class Arrow {

    public static void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double angle = Math.atan2(dy, dx);

        int len = 10;
        
        // Dessiner la ligne
        g2d.drawLine(x1, y1, x2, y2);

        // Dessiner la pointe de la flèche
        Polygon arrowHead = new Polygon();
        arrowHead.addPoint((int) (x2 - len * Math.cos(angle - Math.PI / 6)), (int) (y2 - len * Math.sin(angle - Math.PI / 6)));
        arrowHead.addPoint((int) (x2 - len * Math.cos(angle + Math.PI / 6)), (int) (y2 - len * Math.sin(angle + Math.PI / 6)));
        arrowHead.addPoint(x2, y2);

        g2d.fill(arrowHead);
    }
}
