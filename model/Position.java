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

    // score + animation de capture
    private int score = 0;
    private boolean flashCapture = false;

    // Récupérer la hauteur de l'attribut 
    public synchronized int getHauteur() {
        return hauteur; 
    }

    // Récupérer le score
    public synchronized int getScore() {
        return score;
    }

    // Ajouter un score
    public synchronized void addScore(int delta) {
        score += delta;
    }

    public synchronized  boolean isFlashCapture() {
        return flashCapture;
    }

    // Mettre à jour le flash de capture
    public synchronized void setFlashCapture(boolean v) {
        flashCapture = v;
    }

    // Faire avancer la ligne de parcours
    public synchronized void avancer() {
        avancement += VITESSE_AVANCEMENT; 
    }

    // Récupérer l'avancement de la position
    public synchronized int getAvancement() {
        return avancement; 
    }

    // Mettre à jour l'avancement de la position
    public synchronized void setAvancement(int avancement) {
        this.avancement = avancement; 
    }
    
    // Ajouter un saut à la hauteur 
    public synchronized void jump() {
        hauteur += IMPULSION; // Dans le bon sens (avec un +)
        if(hauteur > HAUTEUR_MAX - HAUTEUR_OVALE)
            hauteur = HAUTEUR_MAX - HAUTEUR_OVALE; // Eviter de sortir de l'ecran
    }

    // Descend tout seul 
    public synchronized void move () {
            hauteur -= VITESSE_CHUTE; // Dans le bon sens (avec un -)
            if (hauteur < HAUTEUR_MIN)
                hauteur = HAUTEUR_MIN; 
    }
}