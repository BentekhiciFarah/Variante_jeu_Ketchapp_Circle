package model; 
public class Position {
    // Définition des constantes

    // Impulsion à chaque saut 
    public static final int IMPULSION = 20; 

    // Hauteur de l'ovale 
    public static final int HAUTEUR_OVALE = 40; 

    // Borne de la hauteur 
    public static final int HAUTEUR_MIN = 0; 
    public static final int HAUTEUR_MAX = 200; 

    // Horizon 
    public static final int BEFORE = 50; 
    public static final int AFTER = 200;

    // hauteur au début + vitesse 
    private int hauteur = 0;  
    public static final int VITESSE_CHUTE = 2;

    // Récupérer la hauteur de l'attribut 
    public int getHauteur() {
        return hauteur; 
    }

    // Ajouter un saut à la hauteur 
    public void jump() {
        hauteur += IMPULSION; // Dans le bon sens (avec un +)
        if(hauteur > HAUTEUR_MAX - HAUTEUR_OVALE)
            hauteur = HAUTEUR_MAX - HAUTEUR_OVALE; // Eviter de sortir de l'ecran
    }

    // Descend tout seul 
    public void move () {
            hauteur -= VITESSE_CHUTE; // Dans le bon sens (avec un -)
            if (hauteur < HAUTEUR_MIN)
                hauteur = HAUTEUR_MIN; 
    }
}