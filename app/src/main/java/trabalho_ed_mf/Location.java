package trabalho_ed_mf;

import ClassImplementation.LinkedList;

/**
 * Representa uma localização no mapa do jogo.
 */
public class Location {
    /**
     * Bandeira associada à localização.
     */
    private int index;

    private LinkedList<Bot> bots;


    /**
     * Construtor que inicializa uma localização com valores padrão.
     *
     * @param i O índice da localização.
     */
    public Location(int i) {
        this.index = i;
        this.bots = new LinkedList<>();
    }
    public void addBot(Bot bot) {
        bots.add(bot);
    }
     public void removeBot(Bot bot) {
        bots.remove(bot);
     }

    /**
     * Obtém se a localização tem um bot.
     *
     * @return true se a localização tem um bot, false caso contrário.
     */
    public boolean getHasBot() {
        return !bots.isEmpty();
    }
    /**
     * Obtém o índice da localização.
     *
     * @return O índice da localização.
     */
    public int getIndex() {
        return index;
    }
    public LinkedList<Bot> getBots() {
        return bots;
    }

    /**
     * Define o índice da localização.
     *
     * @param index O índice da localização.
     */
    public void setIndex(int index) {
        this.index = index;
    }

}