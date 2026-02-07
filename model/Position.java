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

    // Vitesse de chute
    public static final int VITESSE_CHUTE = 2;

    // hauteur au début  
    private int hauteur = HAUTEUR_MAX / 2;  

    // Avancement de la position
    private int avancement = 0;

    // Vitesse d'avancementé
    public static final int VITESSE_AVANCEMENT = 1; 

    // Récupérer la hauteur de l'attribut 
    public int getHauteur() {
        return hauteur; 
    }

    // Faire avancer la ligne de parcours
    public void avancer() {
        avancement += VITESSE_AVANCEMENT; 
    }

    public int getAvancement() {
        return avancement; 
    }

    public void setAvancement(int avancement) {
        this.avancement = avancement; 
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