package trabalho_ed_mf;


/**
 * Representa uma bandeira no jogo associada a um jogador.
 */

public class Flag {
    /** Cor da bandeira. */
    private PlayerColour colour;
    /** Indice que indica a posição da bandeira no mapa. */
    private int index;

    /**
     * Construtor que cria uma bandeira com uma cor específica.
     *
     * @param colour A cor da bandeira, representada pelo enum PlayerColour.
     */
    public Flag(String colour){
        this.colour = PlayerColour.valueOf(colour);
        this.index = -1;
    }

    /**
    * Construtor padrão que cria uma bandeira sem cor e com índice -1.
    */
    public Flag(){
        this.colour = null;
        this.index = -1;
    }


     /**
     * Obtém a cor da bandeira.
     *
     * @return A cor da bandeira.
     */
    public PlayerColour  getColour(){
        return colour;
    }

    /**
     * Define a cor da bandeira.
     *
     * @param colour A cor da bandeira, representada pelo enum PlayerColour.
     */
    public void setColour(PlayerColour colour){
        this.colour = colour;
    }

    /**
     * Obtém o índice da bandeira no mapa.
     *
     * @return O índice da bandeira no mapa.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Define o índice da bandeira no mapa.
     *
     * @param index O índice da bandeira no mapa.
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
