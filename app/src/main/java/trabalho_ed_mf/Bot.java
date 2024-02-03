package trabalho_ed_mf;


import static trabalho_ed_mf.BotMovement.*;

/**
 * Representa um bot no jogo, caracterizado pelo sua localização atual, localização anterior, o índice no
 * grafo e algoritmo de movimentação.
 */

public class Bot {
    /**
     * Localizão atual do bot.
     */
    private int location;
    /**
     * Algoritmo de movimentação do bot.
     */
    private MovementEnum movEnum;
    /**
     * Contador de bots.
     */
    private static int contador;
    /**
     * Indice do bot.
     */
    private int index;

    /**
     * Construtor da classe Bot.
     *
     * @param player Player a que o bot está associado
     * @param mov    Algoritmo de movimentação do bot.
     */
    public Bot(Player player, MovementEnum mov) {
        contador++;
        this.index = contador;
        this.location = player.getBase().getIndex();
        this.movEnum = mov;
    }


    public int move(Map map, int targetIndex) {
        System.out.println("\nBot used " + movEnum);
        System.out.print("Bot " + index + " moved from " + location);
        if (movEnum == MovementEnum.SHORTESTPATH) {
            location = shortestPath(map, location, targetIndex);
        } else if (movEnum == MovementEnum.SHORTESTCONEECTEDPATH) {
            location = minimumSpanningTree(map, location, targetIndex);
        } else if (movEnum == MovementEnum.RANDOMPATH) {
            location = randomPath(map, location);
        }
        System.out.println(" to " + location);
        return location;
    }

    /**
     * Obtém o índice da posição atual do bot no mapa.
     *
     * @return O índice da posição do bot.
     */
    public int getLocation() {
        return location;
    }

    /**
     * Define o índice da posição do bot no mapa.
     *
     * @param index O novo índice da posição do bot.
     */
    public void setIndex(int index) {
        this.location = index;
    }

    /**
     * Define o algoritmo de movimento para o bot.
     *
     * @param mov O algoritmo de movimento a ser definido para o bot.
     */
    public void setMov(MovementEnum mov) {
        this.movEnum = mov;
    }

    /**
     * Obtém o algoritmo de movimento do bot.
     *
     * @return O algoritmo de movimento do bot.
     */
    public MovementEnum getMovEnum() {
        return movEnum;
    }

}

