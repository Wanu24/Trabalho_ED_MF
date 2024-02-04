package trabalho_ed_mf;


/**
 * Representa uma bandeira no jogo associada a um jogador.
 */

public class Flag {
    /**
     * Cor da bandeira.
     */
    private PlayerColour colour;
    /**
     * Bot que está a carregar a bandeira.
     */
    private Bot CarryBot;


    /**
     * Construtor que cria uma bandeira com uma cor específica.
     *
     * @param colour A cor da bandeira, representada pelo enum PlayerColour.
     */
    public Flag(String colour) {
        this.colour = PlayerColour.valueOf(colour);
        this.CarryBot = null;
    }

    /**
     * Construtor padrão que cria uma bandeira sem cor.
     */
    public Flag() {
        this.colour = null;
        this.CarryBot = null;
    }

    /**
     * Adiciona um bot à bandeira.
     *
     * @param bot
     */
    public void addBot(Bot bot) {
        this.CarryBot = bot;
    }

    /**
     * Remove o bot da bandeira.
     */
    public void removeBot() {
        this.CarryBot = null;
    }

    /**
     * Retorna o bot que está a carregar a bandeira.
     *
     * @return
     */
    public Bot getCarryBot() {
        return CarryBot;
    }

    /**
     * Obtém a cor da bandeira.
     *
     * @return A cor da bandeira.
     */
    public PlayerColour getColour() {
        return colour;
    }

    /**
     * Define a cor da bandeira.
     *
     * @param colour A cor da bandeira, representada pelo enum PlayerColour.
     */
    public void setColour(PlayerColour colour) {
        this.colour = colour;
    }


}
