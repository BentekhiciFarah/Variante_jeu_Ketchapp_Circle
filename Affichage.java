import java.awt.Dimension;
import javax.swing.JPanel; 
import java.awt.Graphics; 

public class Affichage extends JPanel {
    // Définition des constantes pour les dimensions 
    public static final int X = 700; // largeur 
    public static final int Y = 500; // hauteur 

    // Dimensions qui ne bougent pas 
    public static final int OVALE_X = 100; 
    public static final int OVALE_LARGEUR = 150; 
    public static final int OVALE_HAUTEUR = 80; 

    // La position initiale de l'ovale 
    private Position pos; 

    // Constructeur : Fixation des dimensions du JPanel en utilisant les constantes 
    public Affichage(Position p) {
        pos = p; 
        setPreferredSize(new Dimension(X, Y)); 
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        g.drawOval(OVALE_X, pos.getHauteur(), OVALE_LARGEUR, OVALE_HAUTEUR); 
    }

}