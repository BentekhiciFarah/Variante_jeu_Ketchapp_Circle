package model; 

// thread qui déclenche une animation de capture d'objets
public class AnimationCapture extends Thread {
    private final Position pos;
    public static final int DUREE_MS = 150;

    public AnimationCapture(Position p) {
        this.pos = p;
    }

    @Override
    public void run() {
        pos.setFlashCapture(true);
        try { Thread.sleep(DUREE_MS); }
        catch (InterruptedException e) { e.printStackTrace(); }
        pos.setFlashCapture(false);
    }
}