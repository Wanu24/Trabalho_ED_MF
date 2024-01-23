package trabalho_ed_mf;


import static trabalho_ed_mf.BotMovement.*;

/*
 * Representa um bot no jogo, caracterizado pelo sua localização atual, localização anterior, o índice no
 * grafo e algoritmo de movimentação. 
 */

public class Bot{
    /** Localizão atual do bot. */
    private int location;
    /** Localizão anterior do bot. */
    private int lastLocation;
    /** Algoritmo de movimentação do bot. */
    private MovementEnum movEnum;
    /** Contador de bots. */
    private static int contador;
    /** Índice do bot. */
    private int index;

    /** Construtor da classe Bot.
     * 
     * @param player    Player a que o bot está associado
     * @param mov   Algoritmo de movimentação do bot. 
     */
    public Bot(Player player,MovementEnum mov){
        contador++;
        this.index = contador;
        this.location = player.getFlag().getIndex();
        this.lastLocation = player.getFlag().getIndex();
        this.movEnum = mov;
    }

    /**
    * Move o bot para uma nova posição no mapa com base no algoritmo de movimento selecionado.
    * Antes de mover, atualiza a informação de localização do bot no grafo do mapa.
    *
    * @param map    O mapa no qual o bot está se movendo.
    * @param enemy  O jogador inimigo, usado como referência para alguns algoritmos de movimento.
    */
    public void move(Map map,Player enemy){
        map.getNetwork().getVertex(location).setHasBot(false);
        lastLocation = location;
        if(movEnum == MovementEnum.SHORTESTPATH){
            location = shortestPath(map,location, enemy.getFlag().getIndex());
        }
        else if(movEnum == MovementEnum.RANDOMPATH){
            location = randomPath(map,location);
        }
        else if(movEnum == MovementEnum.ATHELETICPATH){
            location = atheleticPath(map,location,lastLocation);
        }
        map.getNetwork().getVertex(location).setHasBot(true);
        System.out.println("Bot used " + movEnum);
        System.out.println("Bot " + index +" moved from " + lastLocation + " to " + location);
    }

    /**
    * Obtém o índice da posição atual do bot no mapa.
    *
    * @return O índice da posição do bot.
    */
    public int getIndex(){
        return location;
    }

    /**
    * Define o índice da posição do bot no mapa.
    *
    * @param index O novo índice da posição do bot.
    */
    public void setIndex(int index){
        this.location = index;
    }

    /**
    * Define o algoritmo de movimento para o bot.
    *
    * @param mov O algoritmo de movimento a ser definido para o bot.
    */
    public void setMov(MovementEnum mov){
        this.movEnum = mov;
    }

    /**
    * Obtém o algoritmo de movimento do bot.
    * @return O algoritmo de movimento do bot.
    */
    public MovementEnum getMovEnum(){
        return movEnum;
    }
}

