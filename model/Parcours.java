package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// générer une ligne brisée sous la forme d’une liste de points
public class Parcours {
    // la liste des points
    private List<Point> points;
      // l'écart en X entre XMIN et XMAX 
    public static final int PAS_X_MIN = 30;
    public static final int PAS_X_MAX = 60;
    // l'écart en Y entre -DELTAY et +DELTAY
    public static final int DELTA_Y = 30;
    private static final Random random = new Random();

    public Parcours() {
        points = new ArrayList<>();
        int hauteur_y = (Position.HAUTEUR_MIN + Position.HAUTEUR_MAX) / 2;
        genererLigneBrisee(hauteur_y);
    }

    private void genererLigneBrisee(int hauteurInitiale) {
        // 1 er point avant BEFORE
        int x = -Position.BEFORE;
        int y = hauteurInitiale;
        points.add(new Point(x, y));

        // 2e point à BEFORE + PAS X_MAX
         x += PAS_X_MIN;
        points.add(new Point(x, y));

        // tant qu'on n'a pas dépassé BEFORE + AFTER
        while (x <= Position.AFTER) {
            // on augmente de PAS_X_MIN à PAS_X_MAX
            x += random.nextInt(PAS_X_MAX - PAS_X_MIN) + PAS_X_MIN;
            // on choisit une nouvelle ordonnée aléatoire qui soit au plus à DELTA_Y de la précédente
            // mais qui ne sort pas des bornes
            int deltaY = random.nextInt(2 * DELTA_Y) - DELTA_Y;
            int newY = y + deltaY;

            // bornage Y
            if (newY < Position.HAUTEUR_MIN) newY = Position.HAUTEUR_MIN;
            if (newY > Position.HAUTEUR_MAX) newY = Position.HAUTEUR_MAX;

            y = newY;
            points.add(new Point(x, y));
        }
    }
    

    public List<Point> getPoints() {
        return points;
    }
}