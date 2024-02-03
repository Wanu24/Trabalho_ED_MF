package trabalho_ed_mf;


/**
 * Representa uma bandeira no jogo associada a um jogador.
 */

public class Flag {
    /** Cor da bandeira. */
    private PlayerColour colour;
    private Bot CarryBot;


    /**
     * Construtor que cria uma bandeira com uma cor específica.
     *
     * @param colour A cor da bandeira, representada pelo enum PlayerColour.
     */
    public Flag(String colour){
        this.colour = PlayerColour.valueOf(colour);
        this.CarryBot = null;
    }

    public void addBot(Bot bot){
        this.CarryBot = bot;
    }
    public void removeBot(){
        this.CarryBot = null;
    }
     public Bot getCarryBot(){
        return CarryBot;
     }
    /**
    * Construtor padrão que cria uma bandeira sem cor.
    */
    public Flag(){
        this.colour = null;
        this.CarryBot = null;
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


}
