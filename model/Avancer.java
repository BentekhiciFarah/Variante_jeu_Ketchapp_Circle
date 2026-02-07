package model; 

// classe pour faire avancer la position
public class Avancer extends Thread {
    private final Position pos;
    private final Parcours parcours;
    public static final int DELAY = 50;

    public Avancer(Position pos, Parcours parcours) {
        this.pos = pos;
        this.parcours = parcours; 
    }

    @Override
    public void run() {
        while (true) {
            pos.avancer();
            parcours.updatePourAvancement();
            try {
                Thread.sleep(DELAY); // Attendre 50 ms avant de faire avancer à nouveau
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}