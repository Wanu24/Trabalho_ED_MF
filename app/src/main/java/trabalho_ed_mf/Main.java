// Main.java
package trabalho_ed_mf;

import GUI.*;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {

        Game game = new Game();
        CountDownLatch playerLatch = new CountDownLatch(2); // Create a CountDownLatch with a count of 2

        SwingUtilities.invokeLater(() -> {
            PlayerCreationGUI playerCreationGUI = new PlayerCreationGUI(game, playerLatch); // Pass the CountDownLatch to PlayerCreationGUI
            playerCreationGUI.setVisible(true);
        });

        playerLatch.await(); // Wait here until two players are created

        game.coinFlip();

        CountDownLatch mapLatch = new CountDownLatch(1); // Create a CountDownLatch with a count of 1

        SwingUtilities.invokeLater(() -> {
            MapOptionGUI mapOptionGUI = new MapOptionGUI(game, mapLatch);
            mapOptionGUI.setVisible(true);
        });

        mapLatch.await(); // Wait here until the map is created

        CountDownLatch flagLatch = new CountDownLatch(1); // Create a CountDownLatch with a count of 1

        SwingUtilities.invokeLater(() -> {
            FlagSelectionGUI flagSelectionGUI = new FlagSelectionGUI(game, flagLatch);
            flagSelectionGUI.setVisible(true);
        });

        flagLatch.await(); // Wait here until the flags are selected

        CountDownLatch botLatch = new CountDownLatch(1); // Create a CountDownLatch with a count of 1

        SwingUtilities.invokeLater(() -> {
            BotCreationGUI botCreationGUI = new BotCreationGUI(game, botLatch);
            botCreationGUI.setVisible(true);
        });

        botLatch.await(); // Wait here until the bots are created

        int i = 0;
        int counter = 1;
        while(i !=1){
            System.out.println("Round: " + counter);
            i = game.useTurn(counter);
            sleep(1000);
            counter++;
        }
    }
}