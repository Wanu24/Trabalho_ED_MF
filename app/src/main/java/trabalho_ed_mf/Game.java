package trabalho_ed_mf;

import ClassImplementation.LinkedList;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
    private Map map;
    private LinkedList<Player> players;
    private static int roundNumber;

    public Game() {
        this.map = new Map();
        this.players = new LinkedList<Player>();
        roundNumber = 0;
    }

    public int lerInt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido! Insira novamente: ");
            return 1;
        }
    }
    public float lerFloat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Float.parseFloat(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido! Insira novamente: ");
            return 1;
        }
    }



    public String ler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }


    public void createMap() throws IOException {
        System.out.println("Insira o tamanho do mapa: ");
        int size = 0;
        try {
            size = lerInt();
        } catch (IOException e) {
            System.out.println("Erro na leitura do tamanho do mapa!");
        }
        map.setSize(size);
        for (int i = 0; i < size; i++) {
            Location local = new Location(i);
            map.addLocal(local);
        }
        System.out.println("Escolha entre direcional e bidirecional: ");
        String type = "";
        try {
            type = ler();
        } catch (IOException e) {
            System.out.println("Erro na leitura do tipo de mapa!");
        }
        System.out.println("Insira a densidade de arestas: ");
        float densidade = lerFloat();
        int numArestas = 0;
        if (type.equals("direcional")) {
            numArestas = (int) (densidade * (size * (size - 1)));
        } else if (type.equals("bidirecional")) {
            numArestas = (int) (densidade * (size * (size - 1)) / 2);
        } else {
            System.out.println("Tipo de mapa invalido!");
        }
        int count = 0;
        System.out.println(numArestas);
        while (count < numArestas) {
            Random random = new Random();
            int randomNumber1 = random.nextInt(size);
            int randomNumber2 = random.nextInt(size);
            if (randomNumber1 != randomNumber2) {
                if (!map.getNetwork().hasEdge(randomNumber1, randomNumber2)) {
                    int distance = randomNumber(15) + 1;
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

    public int useTurn() {
        Player player = players.get(roundNumber % 2);
        Player enemy = players.get((roundNumber + 1) % 2);
        player.useTurn(map, enemy);
        roundNumber++;
        System.out.println(player.getLastBot().getIndex()+" "+enemy.getFlag().getIndex());///////////////////////////////////////////////////////////
        if (player.getLastBot().getIndex() == enemy.getFlag().getIndex()) {
            System.out.println("Bot do jogador " + player.getName() + " chegou a bandeira do jogador " + enemy.getName() + "!");
            System.out.println("Jogador " + player.getName() + " ganhou!");
            System.out.println("Ronda" + roundNumber);
            return 1;
        }
        return 0;
    }

    public void createBots() throws IOException {
        for (int i = 0; i < players.size(); i++) {
            createBotsPlayer(players.get(i));
        }
    }

    public void createBotsPlayer(Player player) throws IOException {
        System.out.println(player.getName() + " insira o numero de bots: ");
        int numBots = lerInt();
        int numVertices = map.getNetwork().size();
        if (numBots <= (numVertices / 5)) {
            for (int i = 0; i < numBots; i++) {
                player.addBot(new Bot(player));
            }
        } else {
            System.out.println("Numero de bots invalido!");
            createBotsPlayer(player);
        }
    }

    public void chooseFlags() throws IOException {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player " + players.get(i).getName() + " choose a flag: ");
            int index = lerInt();
            if (map.getNetwork().getVertex(index).getFlag() == null) {
                players.get(i).getFlag().setIndex(index);
                map.addFlag(index,(players.get(i).getFlag()));
            } else {
                System.out.println("Local ja tem bandeira!");
                i--;
            }
        }
    }

    public void coinFlip() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if(randomNumber == 1){
            Player player = players.get(0);
            players.remove(0);
            players.add(player);
        }
    }

    public void createPlayers() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Insira o nome do jogador " + (i + 1) + ": ");
            String name = "";
            try {
                name = ler();
            } catch (IOException e) {
                System.out.println("Erro na leitura do nome do jogador!");
            }
            Flag flag = new Flag();
            if (i == 1) {
                flag.setColour(PlayerColour.valueOf("BLACK"));
            } else {
                flag.setColour(PlayerColour.valueOf("WHITE"));
            }
            Player player = new Player(name, flag);
            players.add(player);
        }
    }
}