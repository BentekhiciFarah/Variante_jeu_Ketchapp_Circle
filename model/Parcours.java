package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Parcours {
    private final List<Point> points;
    private final List<Objet> objets;
    private final Position pos;

    public static final int PAS_X_MIN = 30;
    public static final int PAS_X_MAX = 35;
    public static final int DELTA_Y = 35;

    private static final Random random = new Random();

    public Parcours(Position pos) {
        this.pos = pos;
        this.points = new ArrayList<>();
        this.objets = new ArrayList<>();
        genererLigneBrisee(pos.getHauteur());
    }

    private void genererLigneBrisee(int hauteurInitiale) {
        int x = -Position.BEFORE;
        int y = hauteurInitiale;
        points.add(new Point(x, y));

        x += PAS_X_MIN;
        points.add(new Point(x, y));

        while (x < Position.AFTER) {
            x += random.nextInt(PAS_X_MAX - PAS_X_MIN) + PAS_X_MIN;

            int deltaY = random.nextInt(2 * DELTA_Y + 1) - DELTA_Y;
            int newY = y + deltaY;

            if (newY < Position.HAUTEUR_MIN) newY = Position.HAUTEUR_MIN;
            if (newY > Position.HAUTEUR_MAX) newY = Position.HAUTEUR_MAX;

            y = newY;
            points.add(new Point(x, y));
        }
    }

    // ---- GETTERS DECALÉs (la vue ne modifie pas le modèle) ----

    public synchronized List<Point> getPoints() {
        int av = pos.getAvancement();
        List<Point> decalage = new ArrayList<>(points.size());
        for (Point p : points) {
            decalage.add(new Point(p.x - av, p.y));
        }
        return decalage;
    }

    public synchronized List<Objet> getObjets() {
        int av = pos.getAvancement();
        List<Objet> res = new ArrayList<>();
        for (Objet o : objets) {
            if (!o.capture) {
                // copie décalée pour affichage
                Objet c = new Objet(o.x - av, o.y);
                res.add(c);
            }
        }
        return res;
    }

    // ---- LIGNE INFINIE ----

    public synchronized void updatePourAvancement() {
        int av = pos.getAvancement();

        // Supprimer les points à gauche : si le 2e est déjà sorti, on enlève le 1er
        while (points.size() > 2 && (points.get(1).x - av) < -Position.BEFORE) {
            points.remove(0);
        }

        // Ajouter des points à droite si la fin approche l'horizon
        while ((points.get(points.size() - 1).x - av) < Position.AFTER) {
            ajouterPointFin();
        }

        // Nettoyage objets (capturés ou trop à gauche)
        objets.removeIf(o -> o.capture || (o.x - av) < -Position.BEFORE);
    }

    private void ajouterPointFin() {
        Point last = points.get(points.size() - 1);

        int x = last.x + random.nextInt(PAS_X_MAX - PAS_X_MIN + 1) + PAS_X_MIN;

        int deltaY = random.nextInt(2 * DELTA_Y + 1) - DELTA_Y;
        int y = last.y + deltaY;

        if (y < Position.HAUTEUR_MIN) y = Position.HAUTEUR_OVALE;
        if (y > Position.HAUTEUR_MAX) y = Position.HAUTEUR_OVALE;

        points.add(new Point(x, y));
    }

    // ---- GENERATION OBJETS ----

    public synchronized void genererObjetPresDeLaLigne(Random rnd, int offMin, int offMax, int decalY) {
        Point last = points.get(points.size() - 1);

        int x = last.x + rnd.nextInt(offMax - offMin + 1) + offMin;

        int signe = rnd.nextBoolean() ? 1 : -1;
        int y = last.y + signe * (rnd.nextInt(decalY) + 5);

        if (y < Position.HAUTEUR_MIN) y = Position.HAUTEUR_OVALE;
        if (y > Position.HAUTEUR_MAX) y = Position.HAUTEUR_OVALE;

        objets.add(new Objet(x, y));
    }

    // ---- CAPTURE + SCORE ----
    // retourne true si au moins 1 capture a eu lieu (pour déclencher animation)
    public synchronized boolean testerCaptures(Position pos, int ovaleXModele, int margeX) {
        int av = pos.getAvancement();

        int yTop = pos.getHauteur();
        int yBottom = yTop + Position.HAUTEUR_OVALE;

        boolean captureAuMoinsUne = false;

        for (Objet o : objets) {
            if (o.capture) continue;

            int xCorrige = o.x - av; // position visible dans le repère modèle
            if (Math.abs(xCorrige - ovaleXModele) <= margeX) {
                if (o.y >= yTop && o.y <= yBottom) {
                    o.capture = true;
                    pos.addScore(1);
                    captureAuMoinsUne = true;
                }
            }
        }
        return captureAuMoinsUne;
    }
}
