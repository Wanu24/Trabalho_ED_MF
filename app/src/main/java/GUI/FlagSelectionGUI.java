// FlagSelectionGUI.java
package GUI;

import trabalho_ed_mf.Game;
import trabalho_ed_mf.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.CountDownLatch;

/**
 * Uma classe GUI para seleção de bandeiras, onde os jogadores escolhem bandeiras para Jogador 1 e Jogador 2.
 */
public class FlagSelectionGUI extends JFrame {

    private JComboBox<Integer> player1FlagComboBox;
    private JComboBox<Integer> player2FlagComboBox;
    private JButton confirmSelectionButton;
    private Game game;
    private CountDownLatch latch;

    /**
     * Construtor para criar uma janela de seleção de bandeiras.
     * @param game O jogo.
     * @param latch Um objeto CountDownLatch para sincronizar a seleção de bandeiras.
     */
    public FlagSelectionGUI(Game game, CountDownLatch latch) {
        this.game = game;
        this.latch = latch;
        initComponents();
    }

    /**
     * Inicializa os componentes da janela.
     */
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Flag Selection");

        Integer[] vertices = game.getMap().getVertices();

        player1FlagComboBox = new JComboBox<>(vertices);
        player2FlagComboBox = new JComboBox<>(vertices);

        player1FlagComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    player2FlagComboBox.removeItem(e.getItem());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    player2FlagComboBox.addItem((Integer) e.getItem());
                }
            }
        });

        player2FlagComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    player1FlagComboBox.removeItem(e.getItem());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    player1FlagComboBox.addItem((Integer) e.getItem());
                }
            }
        });

        confirmSelectionButton = new JButton("Confirm Selection");
        confirmSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int player1Flag = (Integer) player1FlagComboBox.getSelectedItem();
                int player2Flag = (Integer) player2FlagComboBox.getSelectedItem();
                game.getPlayers().get(0).getFlag().setIndex(player1Flag);
                game.getMap().addFlag(player1Flag, (game.getPlayers().get(0).getFlag()));
                game.getPlayers().get(1).getFlag().setIndex(player2Flag);
                game.getMap().addFlag(player2Flag, (game.getPlayers().get(1).getFlag()));
                latch.countDown();
                dispose();
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Player 1 Flag:"));
        add(player1FlagComboBox);
        add(new JLabel("Player 2 Flag:"));
        add(player2FlagComboBox);
        add(confirmSelectionButton);

        pack();
        setLocationRelativeTo(null);
    }
}