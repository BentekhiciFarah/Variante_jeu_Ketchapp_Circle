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
    public static final int PAS_X_MAX = 35;

    // l'écart en Y entre -DELTAY et +DELTAY
    public static final int DELTA_Y = 35;
    private static final Random random = new Random();

    private final Position pos;

    public Parcours(Position pos) {
        this.pos = pos;
        points = new ArrayList<>();
        genererLigneBrisee(pos.getHauteur());
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
        while (x < Position.AFTER) {
            // on augmente de PAS_X_MIN à PAS_X_MAX
            x += random.nextInt(PAS_X_MAX - PAS_X_MIN) + PAS_X_MIN;
            // on choisit une nouvelle ordonnée aléatoire qui soit au plus à DELTA_Y de la précédente
            // mais qui ne sort pas des bornes
            int deltaY = random.nextInt(2 * DELTA_Y) - DELTA_Y;
            int newY = y + deltaY;

            // bornage Y
            if (newY < Position.HAUTEUR_MIN) newY = Position.HAUTEUR_OVALE;
            if (newY > Position.HAUTEUR_MAX) newY = Position.HAUTEUR_OVALE;

            y = newY;
            points.add(new Point(x, y));
        }
    }    
    

    public List<Point> getPoints() {
        List<Point> decalage = new ArrayList<>(points.size());
        int dx = pos.getAvancement();
        for (Point p : points)
            decalage.add(new Point(p.x - dx, p.y));
        return decalage;
    }

    // Met à jour la ligne de parcours en fonction de l'avancement
    public synchronized void updatePourAvancement() {
        int av = pos.getAvancement();

        // 1) SUPPRESSION : si le 2e point (corrigé) est sorti à gauche,
        // on supprime le 1er (sinon on perd la continuité du trait)
        while (points.size() > 2 && (points.get(1).x - av) < -Position.BEFORE)
            points.remove(0);
        

        // 2) AJOUT : si le dernier point (corrigé) entre dans l'horizon,
        // on ajoute un nouveau point à droite
        while ((points.get(points.size() - 1).x - av) < Position.AFTER) {
            ajouterPointFin();
        }
    }
    // Ajouter un point à la fin de la ligne
    private void ajouterPointFin() {
        Point last = points.get(points.size() - 1);

        // Générer un nouveau point à droite du dernier, avec une variation aléatoire en Y
        int x = last.x + random.nextInt(PAS_X_MAX - PAS_X_MIN) + PAS_X_MIN;
        int variation = random.nextInt(2 * DELTA_Y + 1) - DELTA_Y;
        int y = last.y + variation;

        // Eviter de sortir des bornes
        if (y < Position.HAUTEUR_MIN / 2) {
            y = Position.HAUTEUR_OVALE;
        } else if (y > Position.HAUTEUR_MAX / 2) {
            y = Position.HAUTEUR_OVALE; 
        }
        // Ajouter le nouveau point à la liste
        points.add(new Point(x, y));
    }

}