package main; 

import control.ReactionClic;
import javax.swing.JFrame;
import model.Avancer;
import model.Descendre;
import model.Parcours;
import model.Position;
import view.Affichage;
import view.Redessine;

public class Main {
    public static void main(String [] args) {
        // Création de la fenetre 
        JFrame maFenetre = new JFrame("L'ovale"); 

        // Définir ce qui se passe quand on ferme la fenetre
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // Créer la position
        Position pos = new Position(); 
        Parcours ligne = new Parcours(pos);

        // Créer le composant d'affichage 
        Affichage affichage = new Affichage(pos, ligne); 

        // L'ajouter à la fenetre 
        maFenetre.add(affichage); 

        // Création d'une instance de Listner de clics 
        ReactionClic rc = new ReactionClic(pos); 
        affichage.addMouseListener(rc); 

        // Adapter la taille de la fenetre 
        maFenetre.pack(); 
        // Centrer 
        maFenetre.setLocationRelativeTo(null); 
        // Afficher la fenetre 
        maFenetre.setVisible(true); 
        // Bloquer la taille de la fenetre 
       // maFenetre.setResizable(false); 

        // Thread qui dessine en continu 
        Redessine nv = new Redessine(affichage); 
        nv.start(); 

        // Thread qui fait descendre automatiquement 
        Descendre desc = new Descendre(pos); 
        desc.start(); 

        // Thread qui fait avancer automatiquement
        Avancer av = new Avancer(pos, ligne);
        av.start();

    }
}