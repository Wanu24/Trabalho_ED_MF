// BotCreationGUI.java
package GUI;

import trabalho_ed_mf.Game;
import trabalho_ed_mf.Player;
import trabalho_ed_mf.MovementEnum;
import trabalho_ed_mf.Bot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class BotCreationGUI extends JFrame {

    private JButton confirmSelectionButton;
    private Game game;
    private CountDownLatch latch;

    public BotCreationGUI(Game game, CountDownLatch latch) {
        this.game = game;
        this.latch = latch;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bot Creation");

        confirmSelectionButton = new JButton("Confirm Selection");
        confirmSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    Player currentPlayer = game.getPlayers().get(i);
                    int numBotsSelected = askForNumBots(currentPlayer);
                    for (int j = 0; j < numBotsSelected; j++) {
                        MovementEnum movementEnumSelected = askForMovementEnum(currentPlayer);
                        currentPlayer.addBot(new Bot(currentPlayer, movementEnumSelected));
                    }
                }
                latch.countDown(); // Call countDown() on the CountDownLatch when the bots are created
                dispose(); // Close the current window
            }
        });

        setLayout(new FlowLayout());
        add(confirmSelectionButton);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private int askForNumBots(Player player) {
        Integer[] numBots = new Integer[game.getMap().getNetwork().size() / 5];
        for (int i = 0; i < numBots.length; i++) {
            numBots[i] = i + 1;
        }
        JComboBox<Integer> numBotsComboBox = new JComboBox<>(numBots);
        JOptionPane.showMessageDialog(null, numBotsComboBox, "Select Number of Bots for " + player.getName(), JOptionPane.QUESTION_MESSAGE);
        return (Integer) numBotsComboBox.getSelectedItem();
    }

    private MovementEnum askForMovementEnum(Player player) {
        MovementEnum[] movementEnums = MovementEnum.values();
        JComboBox<MovementEnum> movementEnumComboBox = new JComboBox<>(movementEnums);

        if (player.getBots().size() < 3) {
            for (int i = 0; i < player.getBots().size(); i++) {
                Bot bot = player.getBots().get(i);
                movementEnumComboBox.removeItem(bot.getMovEnum());
            }
        }

        JOptionPane.showMessageDialog(null, movementEnumComboBox, "Select Movement Enum for " + player.getName(), JOptionPane.QUESTION_MESSAGE);
        return (MovementEnum) movementEnumComboBox.getSelectedItem();
    }
}