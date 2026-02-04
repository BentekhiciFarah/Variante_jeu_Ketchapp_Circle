package model; 
public class Descendre extends Thread {
    // Attribut Position 
    private Position pos; 

    // Délai entre deux déplacements 
    public static final int DELAY = 100;

    // Constructeur : Mémoriser la position 
    public Descendre(Position p) {
        this.pos = p; 
    }

    // Manipulation de ce que fait le thread 
    @Override 
    public void run() {
        while(true) {
            // On décrémente la hauteur 
            pos.move(); 
            // On attend DELAY ms 
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }
    }
}