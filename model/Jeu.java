package model;

// Thread pour simuler le jeu (detection de capture + score)
public class Jeu extends Thread {
    private final Position pos;
    private final Parcours parcours;

    public static final int DELAY = 20;
    public static final int MARGE_CAPTURE_X = 10;

    public static final int OVALE_X_MODELE = 0;

    public Jeu(Position p, Parcours parcours) {
        this.pos = p;
        this.parcours = parcours;
    }

    @Override
    public void run() {
        while (true) {
            boolean capture = parcours.testerCaptures(pos, OVALE_X_MODELE, MARGE_CAPTURE_X);
            if (capture) {
                (new AnimationCapture(pos)).start();
            }

            try { Thread.sleep(DELAY); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
