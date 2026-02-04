package control; 
import model.Position; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 

// But : Gestion des clics sur la souris 
public class ReactionClic extends MouseAdapter {

// Exercice 03
    // Méthode appliquée dès qu'on clique sur le composant 
//    @Override 
//  public void mouseClicked(MouseEvent e) {
        // Affichage de Hello world sur le terminal 
//      System.out.println("Hello World !"); 
//   }

// Exercice 05 
    private Position pos; 

    // Constructeur pour mémoriser la position d'un attribut
    public ReactionClic(Position p) {
        pos = p; 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        pos.jump(); 
    }
}