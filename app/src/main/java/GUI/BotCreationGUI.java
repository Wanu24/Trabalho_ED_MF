// BotCreationGUI.java
package GUI;

import trabalho_ed_mf.Game;
import trabalho_ed_mf.Player;
import trabalho_ed_mf.MovementEnum;
import trabalho_ed_mf.Bot;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

/**
 * Uma classe GUI para criação de bots, onde os jogadores
 * selecionam o número de bots bem como o tipo de movimentação.
 */
public class BotCreationGUI extends JFrame {

    private Game game;
    private CountDownLatch latch;


    /**
     * Construtor para criar uma janela de criação de bots.
     * @param game O jogo.
     * @param latch Um objeto CountDownLatch para sincronizar a criação de bots.
     */
    public BotCreationGUI(Game game, CountDownLatch latch) {
        this.game = game;
        this.latch = latch;
        initComponents();
        createBots(); // Call the bot creation logic directly
    }

    /**
     * Inicializa os componentes da janela.
     */
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bot Creation");

        setLayout(new FlowLayout());

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    /**
     * Cria os bots para cada jogador.
     */
    private void createBots() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player currentPlayer = game.getPlayers().get(i);
            int numBotsSelected = askForNumBots(currentPlayer);
            for (int j = 0; j < numBotsSelected; j++) {
                MovementEnum movementEnumSelected = askForMovementEnum(currentPlayer);
                Bot newBot = new Bot(currentPlayer, movementEnumSelected);
                currentPlayer.addBot(newBot);
                game.getMap().getNetwork().getVertex(currentPlayer.getBase().getIndex()).addBot(newBot);
            }
        }
        latch.countDown();

        SwingUtilities.invokeLater(this::dispose);
    }

    /**
     * Pede ao jogador para selecionar o número de bots
     *
     * @param player jogador para o qual os bots serão selecionados.
     * @return número de bots selecionados.
     */
    private int askForNumBots(Player player) {
        Integer[] numBots = new Integer[game.getMap().getNetwork().size() / 5];
        for (int i = 0; i < numBots.length; i++) {
            numBots[i] = i + 1;
        }
        JComboBox<Integer> numBotsComboBox = new JComboBox<>(numBots);
        JOptionPane.showMessageDialog(null, numBotsComboBox, "Select Number of Bots for " + player.getName(), JOptionPane.QUESTION_MESSAGE);
        return (Integer) numBotsComboBox.getSelectedItem();
    }

    /**
     * Pede ao jogador para selecionar o tipo de movimentação.
     *
     * @param player jogador para o qual o tipo de movimentação será selecionado.
     * @return tipo de movimentação selecionado.
     */
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