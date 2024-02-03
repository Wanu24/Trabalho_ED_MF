package trabalho_ed_mf;


import ClassImplementation.LinkedList;

/**
 * Classe que representa um jogador no jogo.
 */
public class Player {
    /**
     * Nome do Jogador.
     */
    private String name;
    /**
     * Lista de bots do jogador.
     */
    private LinkedList<Bot> botsQueue;
    /**
     * Base associada ao jogador.
     */
    private Base base;


    public Player(String name, Base base) {
        this.name = name;
        this.botsQueue = new LinkedList<Bot>();
        this.base = base;
    }

    /**
     * Obtém a base do jogador.
     *
     * @return Base do jogador.
     */
    public Base getBase() {
        return base;
    }

    /**
     * Define a base do jogador.
     *
     * @param base Nova base do jogador.
     */
    public void setBase(Base base) {
        this.base = base;
    }

    /**
     * Adiciona um bot à fila de bots do jogador.
     *
     * @param bot Bot a ser adicionado.
     */
    public void addBot(Bot bot) {
        this.botsQueue.add(bot);
    }

    /**
     * Remove um bot da fila de bots do jogador.
     *
     * @param bot Bot a ser removido.
     */
    public void removeBot(Bot bot) {
        this.botsQueue.remove(bot);
    }

    /**
     * Obtém o nome do jogador.
     *
     * @return Nome do jogador.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do jogador.
     *
     * @param name Novo nome do jogador.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a fila de bots do jogador.
     *
     * @return Fila de bots do jogador.
     */
    public LinkedList<Bot> getBots() {
        return botsQueue;
    }

    /**
     * Define a fila de bots do jogador.
     *
     * @param bots Nova fila de bots do jogador.
     */
    public void setBots(LinkedList<Bot> bots) {
        this.botsQueue = bots;
    }

    /**
     * Obtém a cor do jogador com base na cor da bandeira.
     *
     * @return Cor do jogador.
     */
    public PlayerColour getPlayerColour() {
        return base.getPlayerColour();
    }

    /**
     * Realiza uma jogada usando um bot contra um inimigo.
     *
     * @param map   Mapa do jogo.
     * @param enemy Jogador inimigo.
     */
    public void useTurn(Map map, Player enemy) {
        Bot bot = botsQueue.get(0);
        Base enemyBase = enemy.getBase();
        int location = bot.getLocation();

        map.getNetwork().getVertex(location).removeBot(bot);

        if (bot == enemyBase.getFlag().getCarryBot()) {
            location = bot.move(map, base.getIndex());
        } else {
            location = bot.move(map, enemyBase.getIndex());
        }


        for (int i = 0; i < map.getNetwork().getVertex(location).getBots().size(); i++) {
            if (map.getNetwork().getVertex(location).getBots().get(i) == base.getFlag().getCarryBot()) {
                base.getFlag().removeBot();
                if (bot == enemyBase.getFlag().getCarryBot()) {
                    enemyBase.getFlag().removeBot();
                }
            }
        }

        if (location == enemyBase.getIndex() && enemyBase.getFlag().getCarryBot() == null) {
            enemyBase.getFlag().addBot(bot);
        }

        botsQueue.remove(0);
        botsQueue.add(bot);
        map.getNetwork().getVertex(location).addBot(bot);
    }
    public Bot getLastBot() {
        return botsQueue.get(botsQueue.size() - 1);
    }
}

