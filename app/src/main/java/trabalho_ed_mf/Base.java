package trabalho_ed_mf;
/**
 * A classe Base representa um objeto base com um índice e uma bandeira.
 */
public class Base {

    /**
     * O índice desta base.
     */
    private int index;
    /**
     * A bandeira associada ao jogador.
     */
    private Flag flag;

    /**
     * Constrói um novo objeto Base com a bandeira especificada.
     * O índice é inicializado como -1.
     *
     * @param flag A bandeira a ser associada a esta base.
     */
    public Base(Flag flag) {
        this.index = -1;
        this.flag = flag;
    }

    /**
     * Retorna o índice desta base.
     *
     * @return O índice desta base.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Define o índice desta base para o valor especificado.
     *
     * @param index O novo índice para esta base.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Retorna a bandeira associada a esta base.
     *
     * @return A bandeira associada a esta base.
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Define a bandeira desta base para o valor especificado.
     *
     * @param flag A nova bandeira para esta base.
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
