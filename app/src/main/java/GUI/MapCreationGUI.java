// MapCreationGUI.java
package GUI;

import trabalho_ed_mf.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

/**
 * Uma classe GUI para criação de mapa.
 */
public class MapCreationGUI extends JFrame {

    private JTextField mapSizeField;
    private JComboBox<String> mapTypeComboBox;
    private JTextField edgeDensityField;
    private JButton createMapButton;
    private Game game;
    private CountDownLatch latch; 

    /**
     * Construtor para criar uma janela de criação de mapa.
     * @param game O jogo.
     * @param latch Um objeto CountDownLatch para sincronizar a criação de mapa.
     */
    public MapCreationGUI(Game game, CountDownLatch latch) { 
        this.game = game;
        this.latch = latch; 
        initComponents();
    }

    /**
     * Inicializa os componentes da janela.
     */
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Map Creation");

        mapSizeField = new JTextField(10);
        mapTypeComboBox = new JComboBox<>(new String[]{"direcional", "bidirecional"});
        edgeDensityField = new JTextField(10);

        createMapButton = new JButton("Create Map");
        createMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(mapSizeField.getText());
                String type = (String) mapTypeComboBox.getSelectedItem();
                float density = Float.parseFloat(edgeDensityField.getText());
                game.createMap(size, type, density);
                latch.countDown(); 
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Map Size:"));
        add(mapSizeField);
        add(new JLabel("Map Type:"));
        add(mapTypeComboBox);
        add(new JLabel("Edge Density:"));
        add(edgeDensityField);
        add(createMapButton);

        pack();
        setLocationRelativeTo(null);
    }
}