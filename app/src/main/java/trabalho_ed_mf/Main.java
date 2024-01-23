package trabalho_ed_mf;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {

        Game game = new Game();

        //mapa criado
        /*Falta !!!!!!!!!!!!!!!!!!!!!!
        opção para importar mapa por ficheiro
        */

        game.createPlayers();
        game.coinFlip();
        game.createMap();
        game.chooseFlags();
        game.createBots();
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