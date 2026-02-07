package view; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Parcours;

public class StartPanel extends JPanel {
    private JButton startButton;
    private JLabel titleLabel;

    public StartPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        titleLabel = new JLabel("The Circle", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(titleLabel, BorderLayout.CENTER);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour démarrer le jeu
                System.out.println("Game Started!");
                // Vous pouvez ajouter ici la logique pour afficher le panneau de jeu
            }
        });
    }
}
