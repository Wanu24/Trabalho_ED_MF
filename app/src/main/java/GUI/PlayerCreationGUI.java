// PlayerCreationGUI.java
package GUI;

import trabalho_ed_mf.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class PlayerCreationGUI extends JFrame {

    private JTextField playerNameField;
    private JButton createPlayerButton;
    private Game game;
    private int playerCount;
    private CountDownLatch latch; // Add a CountDownLatch field

    public PlayerCreationGUI(Game game, CountDownLatch latch) { // Modify the constructor to accept a CountDownLatch
        this.game = game;
        this.playerCount = 0;
        this.latch = latch; // Initialize the CountDownLatch field
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Player Creation");

        playerNameField = new JTextField(10);

        createPlayerButton = new JButton("Create Player");
        createPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText();
                if (!playerName.isEmpty()) {
                    String color = playerCount == 0 ? "BLACK" : "WHITE";
                    game.createPlayer(playerName, color);
                    JOptionPane.showMessageDialog(null, "Successfully created player " + playerName);
                    playerNameField.setText("");
                    playerCount++;
                    latch.countDown(); // Call countDown() on the CountDownLatch each time a player is created
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a player name.");
                }
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Player Name:"));
        add(playerNameField);
        add(createPlayerButton);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }
}