package view; 
public class Redessine extends Thread {

    private Affichage monAffichage; 

    // Délai entre deux raffraichissements 
    public static final int DELAY = 50; // 20 images par seconde 

    // Mémoriser l'affichage à redessiner 
    public Redessine (Affichage a) {
        monAffichage = a; 
    }

    // Ce que le thread exécutera 
    @Override 
    public void run() {
        while(true) {
            // Demander à Swing de redéssiner 
            monAffichage.repaint(); 
            
            // Faire une pause entre deux raffraichissements 
            try {
                Thread.sleep(DELAY); 
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }
    }
}