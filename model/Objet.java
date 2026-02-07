package model; 

// Créer des objets qui seront générés tout le long de la ligne et captés ou pas par l'ovale
public class Objet {
    public int x;
    public int y;
    public boolean capture = false;
    
    public Objet(int x, int y) {
        this.x = x;
        this.y = y;
    }
}