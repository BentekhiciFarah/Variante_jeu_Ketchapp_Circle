
public class Position {
    // Attribut hauteur 
    private int hauteur = 0; 

    // Hauteur d'un saut 
    public static final int HAUTEUR = 20; 
    public static final int VITESSE_CHUTE = 2;

    // Récupérer la hauteur de l'attribut 
    public int getHauteur() {
        return hauteur; 
    }

    // Ajouter un saut à la hauteur 
    public void jump() {
        hauteur -= HAUTEUR; 
        if(hauteur < 0)
            hauteur = 0; // Eviter de sortir de l'ecran
    }

    // Descend tout seul 
    public void move () {
            hauteur += VITESSE_CHUTE; 
    }
}