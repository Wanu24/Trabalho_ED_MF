package trabalho_ed_mf;


import ClassImplementation.LinkedList;
/**
 * Classe que representa um jogador no jogo.
 */
public class Player {
    /** Nome do Jogador. */
    private String name;
    /** Lista de bots do jogador. */
    private LinkedList<Bot> botsQueue;
    /** Bandeira associada ao jogador. */
    private Flag flag;

    /**
     * Construtor para criar um jogador com um nome e uma bandeira.
     * @param name Nome do jogador.
     * @param flag Bandeira associada ao jogador.
     */
    public Player(String name, Flag flag) {
        this.name = name;
        this.botsQueue = new LinkedList<Bot>();
        this.flag = flag;
    }

    /**
     * Construtor para criar um jogador com um nome, uma bandeira e uma fila de bots.
     * @param name Nome do jogador.
     * @param flag Bandeira associada ao jogador.
     * @param bots Fila de bots associada ao jogador.
     */
    public Player(String name, Flag flag, LinkedList<Bot> bots) {
        this.name = name;
        this.botsQueue = bots;
        this.flag = flag;
    }

    /**
     * Adiciona um bot à fila de bots do jogador.
     * @param bot Bot a ser adicionado.
     */
    public void addBot(Bot bot) {
        this.botsQueue.add(bot);
    }

    /**
     * Remove um bot da fila de bots do jogador.
     * @param bot Bot a ser removido.
     */
    public void removeBot(Bot bot) {
        this.botsQueue.remove(bot);
    }

    /**
     * Obtém o nome do jogador.
     * @return Nome do jogador.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do jogador.
     * @param name Novo nome do jogador.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a fila de bots do jogador.
     * @return Fila de bots do jogador.
     */
    public LinkedList<Bot> getBots() {
        return botsQueue;
    }

    /**
     * Define a fila de bots do jogador.
     * @param bots Nova fila de bots do jogador.
     */
    public void setBots(LinkedList<Bot> bots) {
        this.botsQueue = bots;
    }

    /**
     * Obtém a bandeira associada ao jogador.
     * @return Bandeira do jogador.
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Define a bandeira associada ao jogador.
     * @param flag Nova bandeira do jogador.
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    /**
     * Obtém a cor do jogador com base na cor da bandeira.
     * @return Cor do jogador.
     */
    public PlayerColour getPlayerColour() {
        return flag.getColour();
    }

    /**
     * Realiza uma jogada usando um bot contra um inimigo.
     * @param map Mapa do jogo.
     * @param enemy Jogador inimigo.
     */
    public void useTurn(Map map, Player enemy) {
        Bot bot = botsQueue.get(0);
        bot.move(map, enemy);
        botsQueue.remove(0);
        botsQueue.add(bot);
    }

    /**
     * Obtém o último bot na fila do jogador.
     * @return Último bot na fila do jogador.
     */
    public Bot getLastBot() {
        return botsQueue.getRear().getElement();
    }
}

