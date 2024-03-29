package trabalho_ed_mf;

import ClassImplementation.LinkedList;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Representa o jogo e suas funcionalidades, incluindo a criação do mapa, jogadores, bots e a lógica do jogo.
 */
public class Game {
    /**
     * O mapa do jogo.
     */
    private Map map;
    /**
     * Lista de jogadores.
     */
    private LinkedList<Player> players;
    /**
     * Número da ronda atual.
     */
    private static int roundNumber;

    /**
     * Construtor padrão que inicializa o jogo com um mapa vazio e uma lista vazia de jogadores.
     */
    public Game() {
        this.map = new Map();
        this.players = new LinkedList<Player>();
        roundNumber = 0;
    }

    /**
     * Gera um número aleatório entre 0 e max.
     * @param max O valor máximo do número aleatório.
     */
    public int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * Cria um mapa com base no tamanho, tipo e densidade especificados.
     *
     * @param size    O tamanho do mapa.
     * @param type    O tipo de mapa ("direcional" ou "bidirecional").
     * @param density A densidade de arestas no mapa.
     */
    public void createMap(int size, String type, float density) {
        map.setSize(size);
        for (int i = 0; i < size; i++) {
            Location local = new Location(i);
            map.addLocal(local);
        }
        int numArestas = 0;
        if (type.equals("direcional")) {
            numArestas = (int) (density * (size * (size - 1)));
        } else if (type.equals("bidirecional")) {
            numArestas = (int) (density * (size * (size - 1)) / 2);
        } else {
            System.out.println("Tipo de mapa invalido!");
        }
        int count = 0;
        while (count < numArestas) {
            Random random = new Random();
            int randomNumber1 = random.nextInt(size);
            int randomNumber2 = random.nextInt(size);
            if (randomNumber1 != randomNumber2) {
                if (!map.getNetwork().hasEdge(randomNumber1, randomNumber2)) {
                    double distance = randomNumber(15) + 1;
                    if (type.equals("bidirecional")) {
                        map.getNetwork().addEdge(randomNumber2, randomNumber1, distance);
                    } else {
                        map.getNetwork().addEdgeDiretional(randomNumber1, randomNumber2, distance);
                    }
                    count++;
                }
            }
        }
        System.out.println("Mapa criado!");
    }

    /**
     * Executa um turno do jogo para o jogador atual.
     *
     * @param roundNumber O número da rodada atual.
     * @return 1 se o jogador ganhou, 0 se o jogo continua.
     */
    public int useTurn(int roundNumber) {
        Player player = players.get(roundNumber % 2);
        Player enemy = players.get((roundNumber + 1) % 2);
        player.useTurn(map, enemy);
        if (player.getLastBot().getLocation() == player.getBase().getIndex() && player.getLastBot() == enemy.getBase().getFlag().getCarryBot()) {
            System.out.println("Bot do jogador " + player.getName() + " chegou a bandeira do jogador " + enemy.getName() + "!");
            System.out.println("Jogador " + player.getName() + " ganhou na ronda! " + roundNumber);
            return 1;
        }
        return 0;
    }

    /**
     * Obtém a lista de jogadores.
     *
     * @return A lista de jogadores.
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }

    /**
     * Realiza o lançamento de uma moeda para decidir a ordem dos jogadores.
     */
    public void coinFlip() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (randomNumber == 1) {
            Player player = players.get(0);
            players.remove(0);
            players.add(player);
        }
    }

    /**
     * Cria um jogador com base no nome e na cor especificados.
     *
     * @param playerName O nome do jogador.
     * @param colour     A cor do jogador.
     */
    public void createPlayer(String playerName, String colour) {
        Flag flag = new Flag();
        flag.setColour(PlayerColour.valueOf(colour));
        Base base = new Base(flag);
        Player player = new Player(playerName, base);
        players.add(player);
    }

    /**
     * Obtém o mapa do jogo.
     *
     * @return O mapa do jogo.
     */
    public Map getMap() {
        return map;
    }
}