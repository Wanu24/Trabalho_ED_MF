// MapOptionGUI.java
package GUI;

import trabalho_ed_mf.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class MapOptionGUI extends JFrame {

    private JButton createMapButton;
    private JButton importMapButton;
    private Game game;
    private CountDownLatch latch;

    public MapOptionGUI(Game game, CountDownLatch latch) {
        this.game = game;
        this.latch = latch;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Map Options");

        createMapButton = new JButton("Create Map");
        createMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    MapCreationGUI mapCreationGUI = new MapCreationGUI(game, latch);
                    mapCreationGUI.setVisible(true);
                });
                dispose(); // Close the current window
            }
        });

        importMapButton = new JButton("Import Map");
        importMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method in the Game class to import a map
                // game.importMap();
                latch.countDown(); // Call countDown() on the CountDownLatch when the map is imported
                dispose(); // Close the current window
            }
        });

        setLayout(new FlowLayout());
        add(createMapButton);
        add(importMapButton);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }
}