package model; 

import java.util.Random;
// Thread pour générer des objets aléatoires à proximité de la ligne
public class GenerateurObjets extends Thread {
    private final Parcours parcours;
    private final Random rnd = new Random();

    public static final int DELAY = 600;       // fréquence de génération
    public static final int OFFSET_X_MIN = 30; // où on spawn (devant)
    public static final int OFFSET_X_MAX = 140;
    public static final int DECALAGE_Y = 25;   // au-dessus / en dessous de la ligne

    public GenerateurObjets(Parcours p) {
        this.parcours = p;
    }

    @Override
    public void run() {
        while (true) {
            parcours.genererObjetPresDeLaLigne(rnd, OFFSET_X_MIN, OFFSET_X_MAX, DECALAGE_Y);

            try { Thread.sleep(DELAY); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}