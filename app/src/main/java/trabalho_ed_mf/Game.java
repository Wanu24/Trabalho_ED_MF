package trabalho_ed_mf;

import ClassImplementation.LinkedList;
import trabalho_ed_mf.Flag;
import trabalho_ed_mf.PlayerColour;
import trabalho_ed_mf.Bot;
import trabalho_ed_mf.Map;
import trabalho_ed_mf.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
    private Map map;
    private LinkedList<Player> players;
    private static int roundNumber;

    public Game(int i){
        this.map = new Map();
        this.players = new LinkedList<Player>();
        roundNumber = 0;
    }

    public int lerInt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            return Integer.parseInt(br.readLine());
        }catch(NumberFormatException e){
            System.out.println("Valor invalido! Insira novamente: ");
            return 1;
        }
    }

    public String ler() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }


    public void createMap(){
        System.out.println("Insira o tamanho do mapa: ");
        int size = 0;
        try{
            size = lerInt();
        }catch(IOException e){
            System.out.println("Erro na leitura do tamanho do mapa!");
        }
        //create location nodes
        for(int i = 0; i < size; i++){
            Location local = new Location(i);
            map.addLocal(local);
        }
        System.out.println("Escolha entre direcional e bidirecional: ");
        String type = "";
        try{
            type = ler();
        }catch(IOException e){
            System.out.println("Erro na leitura do tipo de mapa!");
        }
        if(type.equals("direcional")){
            for(int i = 0; i < map.getNetwork().size(); i++){
                System.out.println("Insira o numero de ligaçoes do local " + (i+1) + ": ");
                int numLigacoes = 0;
      //random aqui

                for(int j = 0; j < numLigacoes; j++){

                    int index = 0;
                    //random aqui
                    map.getNetwork().getVertex(i).addEdge(map.getNetwork().getVertex(index-1));
                }
            }
        }
        else if(type.equals("bidirecional")){
            for(int i = 0; i < map.getNetwork().size(); i++){
                System.out.println("Insira o numero de ligaçoes do local " + (i+1) + ": ");
                int numLigacoes = 0;
                try{
                    numLigacoes = lerInt();
                }catch(IOException e){
                    System.out.println("Erro na leitura do numero de ligaçoes!");
                }
                for(int j = 0; j < numLigacoes; j++){
                    //random aqui
                }
                    int index = 0;
                    try{
                        index = lerInt();
                    }catch(IOException e){
                        System.out.println("Erro na leitura do local de destino!");
                    }
                    map.getNetwork().addEdge(randomNumber2, randomNumber1, distance);
                    map.getNetwork().getVertex(index-1).addEdge(map.getNetwork().getVertex(i));
                }
            }
        else{
            System.out

        }
    }
    public void useTurn(){
            Player player = players.get(roundNumber%2);
            Player enemy = players.get((roundNumber+1)%2);
            player.useTurn(map,enemy);
    }
    public void createBots() throws IOException{
        System.out.println("Insira o numero de bots: ");
        int numBots = lerInt();
        int numVertices = map.getNetwork().size();
        if(numBots <=(numVertices/5)){
            for(int i = 0; i < players.size(); i++){
                for(int j = 0; j < numBots; j++){
                    map.getNetwork().getVertex(players.get(i).getFlag().getIndex()).setBot(new Bot(players.get(i)));
                }
            }
        }
        else{
            System.out.println("Numero de bots invalido!");
            createBots();
        }
    }

    public void chooseFlags() throws IOException{
        for(int i = 0; i < players.size(); i++){
            System.out.println("Player " + players.get(i).getName() + " choose a flag: ");
            int index = lerInt();
            if(map.getNetwork().getVertex(index).getFlag() == null){
                map.getNetwork().getVertex(index).setFlag(players.get(i).getFlag());
                players.get(i).getFlag().setIndex(index);
            }
            else{
                System.out.println("Local ja tem bandeira!");
                chooseFlags();
            }
        }
    }
    public void initiatePlayer(){
        for(int i = 0; i < 2; i++){
            System.out.println("Insira o nome do jogador " + (i + 1) + ": ");
            String name = "";
            try{
                name = ler();
            }catch(IOException e){
                System.out.println("Erro na leitura do nome do jogador!");
            }
            Flag flag = new Flag();
            if(i==1){
                flag.setColour(PlayerColour.valueOf("BLACK"));
            }
            else {
                flag.setColour(PlayerColour.valueOf("WHITE"));
            }
            Player player = new Player(name, flag);
            players.add(player);
        }
    }
}