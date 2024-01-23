package trabalho_ed_mf;


import java.util.LinkedList;

/**
 * Representa uma localização no mapa do jogo.
 */
public class Location {
    /**
     * Indica se a localização tem uma bandeira.
     */
    private boolean hasFlag;
    /**
     * Indica se a localização tem um bot.
     */
    private boolean hasBot;
    /**
     * Bandeira associada à localização.
     */
    private Flag flag;
    /**
     * Indice que indica a posição da localização no mapa.
     */
    private int index;


    /**
     * Construtor que inicializa uma localização com valores padrão.
     *
     * @param i O índice da localização.
     */
    public Location(int i) {
        this.hasFlag = false;
        this.hasBot = false;
        this.flag = null;
        this.index = i;
    }

    /**
     * Obtém se a localização tem uma bandeira.
     *
     * @return true se a localização tem uma bandeira, false caso contrário.
     */
    public boolean getHasFlag() {
        return hasFlag;
    }

    /**
     * Define se a localização tem uma bandeira.
     *
     * @param hasFlag true se a localização tem uma bandeira, false caso contrário.
     */
    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    /**
     * Obtém se a localização tem um bot.
     *
     * @return true se a localização tem um bot, false caso contrário.
     */
    public boolean getHasBot() {
        return hasBot;
    }

    /**
     * Define se a localização tem um bot.
     *
     * @param hasBot true se a localização tem um bot, false caso contrário.
     */
    public void setHasBot(boolean hasBot) {
        this.hasBot = hasBot;
    }

    /**
     * Obtém a bandeira associada à localização.
     *
     * @return A bandeira associada à localização.
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Define a bandeira associada à localização.
     *
     * @param flag A bandeira a ser associada à localização.
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    /**
     * Obtém o índice da localização.
     *
     * @return O índice da localização.
     */
    public int getIndex() {
        return index;
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