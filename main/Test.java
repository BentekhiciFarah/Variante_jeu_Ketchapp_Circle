package main; 

public class Test {
    public static void main(String[] args) {
        /* Une fenêtre avec pour titre "Test" */
        javax.swing.JFrame Fenetre = new javax.swing.JFrame("Test");
        /* Un bouton "coucou" qui écrit "coucou" dans la console chaque fois qu'on clique dessus */
        javax.swing.JButton Bouton = new javax.swing.JButton("coucou");
        Bouton.addActionListener(e -> System.out.println("coucou"));
        /* Ajouter le bouton à la fenêtre */
        Fenetre.add(Bouton);
        /* Adapter la taille de la fenêtre à son contenu */
        Fenetre.pack();
        /* Afficher la fenêtre */
        Fenetre.setVisible(true);
         /* Fermer la fenêtre quand on clique sur la croix */
        Fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
}