package main;

import control.ReactionClic;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.Avancer;
import model.Descendre;
import model.GenerateurObjets;
import model.Jeu;
import model.Parcours;
import model.Position;
import view.Affichage;
import view.Redessine;
import view.StartPanel;

public class Main {

    private static boolean started = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("TheCircle");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            StartPanel startPanel = new StartPanel(() -> lancerJeu(fenetre));
            fenetre.setContentPane(startPanel);

            fenetre.pack();
            fenetre.setLocationRelativeTo(null);
            fenetre.setVisible(true);
        });
    }

    private static void lancerJeu(JFrame fenetre) {
        if (started) return;
        started = true;

        // MODELE
        Position pos = new Position();
        Parcours ligne = new Parcours(pos);

        // VUE
        Affichage affichage = new Affichage(pos, ligne);

        // CONTROLEUR
        ReactionClic rc = new ReactionClic(pos);
        affichage.addMouseListener(rc);

        // Remplacer la page Start par le jeu
        fenetre.setContentPane(affichage);
        fenetre.pack();
        fenetre.revalidate();
        fenetre.repaint();

        // THREADS
        new Redessine(affichage).start();
        new Descendre(pos).start();
        new Avancer(pos, ligne).start();

        // Si tu n’as pas encore ces classes, commente ces 2 lignes
        new GenerateurObjets(ligne).start();
        new Jeu(pos, ligne).start();
    }
}
