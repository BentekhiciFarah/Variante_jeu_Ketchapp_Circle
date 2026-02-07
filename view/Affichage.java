package view; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Objet;
import model.Parcours;
import model.Position;

public class Affichage extends JPanel {
    // Définition des constantes pour les dimensions de la fenetre  
    public static final int RATIO_X = 3; // largeur 
    public static final int RATIO_Y = 3; // hauteur 

    // Dimensions qui ne bougent pas  
    public static final int OVALE_LARGEUR = 50; 

    // LARGEUR et Position_X en utilisant RATIO
    public static final int LARGEUR = OVALE_LARGEUR * RATIO_X; 
    public final int POSITION_X; // Sera calculée dans le constructeur affichage

    // La position initiale de l'ovale 
    private Position pos; 
    private Parcours ligne;

    private final JButton scoreButton;
    private final JPanel hud;


    // afficher l'ovale et la ligne brisée
    public Affichage(Position p, Parcours lb) {
        pos = p; 
        ligne = lb;

        // Bouton de score
                scoreButton = new JButton("Score : 0");

        // agrandir le texte
        scoreButton.setFont(new Font("Arial", Font.BOLD, 20));

        // agrandir le bouton
        scoreButton.setPreferredSize(new Dimension(200, 50));

        // style HUD
        scoreButton.setFocusable(false);
        scoreButton.setEnabled(false);


        // --- HUD (barre en haut) ---
        hud = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hud.setOpaque(true);
        hud.setBackground(new Color(245, 245, 245));
        hud.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        hud.add(scoreButton);
        add(hud, BorderLayout.NORTH);

        // Dimensions calculées à partir du modèle 
        int largeur_fenetre = (Position.BEFORE + Position.AFTER) * RATIO_X;
        int hauteur_fenetre = (Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) * RATIO_Y;
        setPreferredSize(new Dimension(largeur_fenetre, hauteur_fenetre)); 

        // Calcul de POSITION_X 
        POSITION_X = Position.BEFORE * RATIO_X - LARGEUR / 2;  
    }

    private void dessinerLigneBrisee(Graphics g) {
        List<Point> points = ligne.getPoints();

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            int x1 = (p1.x + Position.BEFORE)* RATIO_X;
            int y1 = (Position.HAUTEUR_MAX - p1.y) * RATIO_Y;

            int x2 = (p2.x + Position.BEFORE) * RATIO_X;
            int y2 = (Position.HAUTEUR_MAX - p2.y) * RATIO_Y;

            g.drawLine(x1, y1, x2, y2);
        }
    }

    // Dessiner les objets en forme de gemme de couleur orange sur la ligne brisée

    private void dessinerObjets(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Antialiasing pour un rendu plus propre
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(255, 140, 0)); // couleur orange

        for (Objet o : ligne.getObjets()) {
            int x = (o.x + Position.BEFORE) * RATIO_X;
            int y = (Position.HAUTEUR_MAX - o.y) * RATIO_Y;

            int size = 14;

            // forme diamant (losange)
            int[] xs = {
                x,
                x + size / 2,
                x,
                x - size / 2
            };

            int[] ys = {
                y - size / 2,
                y,
                y + size / 2,
                y
            };

            g2.fillPolygon(xs, ys, 4);

            // petit contour pour le style
            g2.setColor(Color.BLACK);
            g2.drawPolygon(xs, ys, 4);
            g2.setColor(new Color(255, 140, 0));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        // Afficher le score dans le bouton du HUD
        scoreButton.setText("Votre Score : " + pos.getScore());


        dessinerLigneBrisee(g);
        dessinerObjets(g);

        // Dessiner l'ovale à sa position actuelle
        int OVALE_Y = (Position.HAUTEUR_MAX - pos.getHauteur() - Position.HAUTEUR_OVALE) * RATIO_Y; 
        int HAUTEUR_OVALE = Position.HAUTEUR_OVALE * RATIO_Y; 
        g.drawOval(POSITION_X, OVALE_Y, LARGEUR, HAUTEUR_OVALE); 

        // Animation capture (simple)
        if (pos.isFlashCapture()) {
            g.drawString("+1", POSITION_X + LARGEUR / 2, OVALE_Y - 8);
        }
    }

}