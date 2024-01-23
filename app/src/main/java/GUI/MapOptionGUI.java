// MapOptionGUI.java
package GUI;

import trabalho_ed_mf.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

/**
 * Uma classe GUI para opções de mapa.
 */
public class MapOptionGUI extends JFrame {

    private JButton createMapButton;
    private JButton importMapButton;
    private Game game;
    private CountDownLatch latch;

    /**
     * Construtor para criar uma janela de opções de mapa.
     * @param game O jogo.
     * @param latch Um objeto CountDownLatch para sincronizar a criação de mapa.
     */
    public MapOptionGUI(Game game, CountDownLatch latch) {
        this.game = game;
        this.latch = latch;
        initComponents();
    }

    /**
     * Inicializa os componentes da janela.
     */
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
                dispose();
            }
        });

        importMapButton = new JButton("Import Map");
        importMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method in the Game class to import a map
                // game.importMap();
                latch.countDown();
                dispose(); 
            }
        });

        setLayout(new FlowLayout());
        add(createMapButton);
        add(importMapButton);

        pack();
        setLocationRelativeTo(null);
    }
}